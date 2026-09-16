DROP DATABASE IF EXISTS streamflow;

CREATE DATABASE streamflow
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE streamflow;

CREATE TABLE planos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco_mensal DECIMAL(10,2) NOT NULL,
    max_perfis TINYINT NOT NULL DEFAULT 5,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CHECK (preco_mensal >= 0),
    CHECK (max_perfis BETWEEN 1 AND 5)
);

CREATE TABLE assinantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_real VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    cpf CHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    uf CHAR(2) NOT NULL,
    saldo DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    token_pagamento VARCHAR(255),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultima_alteracao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CHECK (saldo >= 0),
    CHECK (cpf REGEXP '^[0-9]{11}$'),
    CHECK (
        uf IN (
            'AC','AL','AP','AM','BA','CE','DF','ES','GO','MA',
            'MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN',
            'RS','RO','RR','SC','SP','SE','TO'
        )
    )
);

CREATE TABLE assinaturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assinante_id INT NOT NULL,
    plano_id INT NOT NULL,
    status ENUM('ativa','cancelada','suspensa') NOT NULL DEFAULT 'ativa',
    data_inicio DATE NOT NULL,
    data_fim DATE,
    valor_mensal DECIMAL(10,2) NOT NULL,
    CHECK (valor_mensal >= 0),
    FOREIGN KEY (assinante_id)
        REFERENCES assinantes(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    FOREIGN KEY (plano_id)
        REFERENCES planos(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE perfis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assinante_id INT NOT NULL,
    nome_exibicao VARCHAR(80) NOT NULL,
    preferencias JSON,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (assinante_id)
        REFERENCES assinantes(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE produtoras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL UNIQUE,
    pais VARCHAR(80),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE series (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produtora_id INT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    ano_lancamento YEAR,
    status ENUM('ativa','removida') NOT NULL DEFAULT 'ativa',
    FOREIGN KEY (produtora_id)
        REFERENCES produtoras(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE temporadas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    serie_id INT NOT NULL,
    numero_temporada INT NOT NULL,
    CHECK (numero_temporada > 0),
    FOREIGN KEY (serie_id)
        REFERENCES series(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    UNIQUE (serie_id, numero_temporada)
);

CREATE TABLE videos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produtora_id INT NOT NULL,
    titulo VARCHAR(180) NOT NULL,
    tipo ENUM('filme','episodio') NOT NULL,
    duracao_segundos INT UNSIGNED NOT NULL,
    classificacao_indicativa VARCHAR(10),
    ano_lancamento YEAR,
    status ENUM('ativo','removido') NOT NULL DEFAULT 'ativo',
    data_entrada_catalogo DATE NOT NULL,
    data_saida_catalogo DATE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CHECK (duracao_segundos > 0),
    FOREIGN KEY (produtora_id)
        REFERENCES produtoras(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE filmes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    video_id INT NOT NULL UNIQUE,
    sinopse TEXT,
    FOREIGN KEY (video_id)
        REFERENCES videos(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE episodios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    video_id INT NOT NULL UNIQUE,
    temporada_id INT NOT NULL,
    numero_episodio INT NOT NULL,
    resumo TEXT,
    CHECK (numero_episodio > 0),
    FOREIGN KEY (video_id)
        REFERENCES videos(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    FOREIGN KEY (temporada_id)
        REFERENCES temporadas(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    UNIQUE (temporada_id, numero_episodio)
);

CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE videos_categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    video_id INT NOT NULL,
    categoria_id INT NOT NULL,
    FOREIGN KEY (video_id)
        REFERENCES videos(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    FOREIGN KEY (categoria_id)
        REFERENCES categorias(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    UNIQUE (video_id, categoria_id)
);

CREATE TABLE reproducoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    perfil_id INT NOT NULL,
    video_id INT NOT NULL,
    categoria_id INT NOT NULL,
    tipo_evento ENUM('PLAY','PAUSE','FINALIZADO') NOT NULL DEFAULT 'PLAY',
    ip_conexao VARCHAR(45) NOT NULL,
    dispositivo ENUM(
        'SmartTV',
        'Smartphone',
        'Web',
        'Tablet',
        'Console'
    ) NOT NULL,
    posicao_segundos INT UNSIGNED NOT NULL DEFAULT 0,
    tempo_consumido_segundos INT UNSIGNED NOT NULL DEFAULT 0,
    registrado_em TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    CHECK (posicao_segundos >= 0),
    CHECK (tempo_consumido_segundos >= 0),
    FOREIGN KEY (perfil_id)
        REFERENCES perfis(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    FOREIGN KEY (video_id)
        REFERENCES videos(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    FOREIGN KEY (video_id, categoria_id)
        REFERENCES videos_categorias(video_id, categoria_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE faturamento_produtoras (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produtora_id INT NOT NULL,
    competencia DATE NOT NULL,
    minutos_consumidos DECIMAL(18,2) NOT NULL DEFAULT 0.00,
    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CHECK (minutos_consumidos >= 0),
    FOREIGN KEY (produtora_id)
        REFERENCES produtoras(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    UNIQUE (produtora_id, competencia)
);

CREATE TABLE auditoria_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tabela VARCHAR(100) NOT NULL,
    operacao VARCHAR(20) NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    valor_antigo JSON,
    valor_novo JSON,
    data_hora TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
);

CREATE INDEX idx_reproducoes_continuar
ON reproducoes (
    perfil_id,
    video_id,
    registrado_em DESC,
    id DESC
);

CREATE INDEX idx_reproducoes_perfil_data
ON reproducoes (
    perfil_id,
    registrado_em DESC,
    id DESC
);

CREATE INDEX idx_reproducoes_bi_periodo
ON reproducoes (
    registrado_em,
    tipo_evento,
    video_id
);

CREATE INDEX idx_reproducoes_dispositivo
ON reproducoes (dispositivo);

CREATE INDEX idx_videos_produtora
ON videos (produtora_id);

CREATE INDEX idx_perfis_assinante
ON perfis (assinante_id);

CREATE INDEX idx_assinantes_uf
ON assinantes (uf);

DELIMITER $$

CREATE FUNCTION minutos_assistidos_por_produtora(
    p_id_produtora INT,
    p_competencia DATE
)
RETURNS DECIMAL(18,2)
NOT DETERMINISTIC
READS SQL DATA
SQL SECURITY DEFINER
BEGIN
    DECLARE v_inicio DATE;
    DECLARE v_fim DATE;
    DECLARE v_minutos DECIMAL(18,2);

    SET v_inicio =
        STR_TO_DATE(
            DATE_FORMAT(p_competencia, '%Y-%m-01'),
            '%Y-%m-%d'
        );

    SET v_fim =
        DATE_ADD(v_inicio, INTERVAL 1 MONTH);

    SELECT
        COALESCE(
            SUM(r.tempo_consumido_segundos) / 60,
            0
        )
    INTO v_minutos
    FROM reproducoes r
    INNER JOIN videos v
        ON v.id = r.video_id
    WHERE v.produtora_id = p_id_produtora
      AND r.registrado_em >= v_inicio
      AND r.registrado_em < v_fim
      AND r.tipo_evento IN ('PAUSE','FINALIZADO');

    RETURN v_minutos;
END$$

CREATE FUNCTION calcular_idade(
    p_data_nascimento DATE
)
RETURNS INT
NOT DETERMINISTIC
NO SQL
SQL SECURITY DEFINER
BEGIN
    RETURN TIMESTAMPDIFF(
        YEAR,
        p_data_nascimento,
        CURDATE()
    );
END$$

CREATE PROCEDURE realizar_cobranca_mensal(
    IN p_id_assinante INT,
    IN p_valor_mensalidade DECIMAL(10,2),
    OUT p_novo_saldo DECIMAL(12,2)
)
SQL SECURITY DEFINER
BEGIN
    DECLARE v_saldo DECIMAL(12,2);
    DECLARE v_mensagem VARCHAR(128);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_mensagem = MESSAGE_TEXT;

        ROLLBACK;

        SET v_mensagem =
            LEFT(
                CONCAT(
                    'Falha na cobranca: ',
                    v_mensagem
                ),
                128
            );

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = v_mensagem;
    END;

    IF p_valor_mensalidade IS NULL
       OR p_valor_mensalidade <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'O valor da mensalidade deve ser maior que zero.';
    END IF;

    START TRANSACTION;

    SET v_saldo = NULL;

    SELECT saldo
    INTO v_saldo
    FROM assinantes
    WHERE id = p_id_assinante
    FOR UPDATE;

    IF v_saldo IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Assinante nao encontrado.';
    END IF;

    IF v_saldo < p_valor_mensalidade THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Saldo insuficiente para realizar a cobranca.';
    END IF;

    UPDATE assinantes
    SET saldo = saldo - p_valor_mensalidade
    WHERE id = p_id_assinante;

    SELECT saldo
    INTO p_novo_saldo
    FROM assinantes
    WHERE id = p_id_assinante;

    COMMIT;
END$$

CREATE PROCEDURE registrar_reproducao(
    IN p_perfil_id INT,
    IN p_video_id INT,
    IN p_ip_conexao VARCHAR(45),
    IN p_dispositivo VARCHAR(20),
    OUT p_reproducao_id BIGINT
)
SQL SECURITY DEFINER
BEGIN
    DECLARE v_categoria_id INT;
    DECLARE v_mensagem VARCHAR(128);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_mensagem = MESSAGE_TEXT;

        ROLLBACK;

        SET v_mensagem =
            LEFT(
                CONCAT(
                    'Falha ao registrar reproducao: ',
                    v_mensagem
                ),
                128
            );

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = v_mensagem;
    END;

    START TRANSACTION;

    IF NOT EXISTS (
        SELECT 1
        FROM perfis
        WHERE id = p_perfil_id
          AND ativo = TRUE
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Perfil inexistente ou inativo.';
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM videos
        WHERE id = p_video_id
          AND status = 'ativo'
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Video inexistente ou removido do catalogo.';
    END IF;

    IF p_ip_conexao IS NULL
       OR TRIM(p_ip_conexao) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Endereco IP obrigatorio.';
    END IF;

    IF p_dispositivo NOT IN (
        'SmartTV',
        'Smartphone',
        'Web',
        'Tablet',
        'Console'
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Tipo de dispositivo invalido.';
    END IF;

    SELECT MIN(categoria_id)
    INTO v_categoria_id
    FROM videos_categorias
    WHERE video_id = p_video_id;

    IF v_categoria_id IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'O video nao possui categoria cadastrada.';
    END IF;

    INSERT INTO reproducoes (
        perfil_id,
        video_id,
        categoria_id,
        tipo_evento,
        ip_conexao,
        dispositivo,
        posicao_segundos,
        tempo_consumido_segundos
    )
    VALUES (
        p_perfil_id,
        p_video_id,
        v_categoria_id,
        'PLAY',
        p_ip_conexao,
        p_dispositivo,
        0,
        0
    );

    SET p_reproducao_id =
        LAST_INSERT_ID();

    COMMIT;
END$$

CREATE PROCEDURE gerar_faturamento_mensal(
    IN p_competencia DATE
)
SQL SECURITY DEFINER
BEGIN
    DECLARE v_finalizado BOOLEAN DEFAULT FALSE;
    DECLARE v_produtora_id INT;
    DECLARE v_minutos DECIMAL(18,2);
    DECLARE v_competencia DATE;
    DECLARE v_mensagem VARCHAR(128);

    DECLARE cursor_produtoras CURSOR
    FOR
        SELECT id
        FROM produtoras
        ORDER BY id;

    DECLARE CONTINUE HANDLER
    FOR NOT FOUND
        SET v_finalizado = TRUE;

    DECLARE EXIT HANDLER
    FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_mensagem = MESSAGE_TEXT;

        ROLLBACK;

        SET v_mensagem =
            LEFT(
                CONCAT(
                    'Falha ao gerar faturamento: ',
                    v_mensagem
                ),
                128
            );

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = v_mensagem;
    END;

    SET v_competencia =
        STR_TO_DATE(
            DATE_FORMAT(p_competencia, '%Y-%m-01'),
            '%Y-%m-%d'
        );

    START TRANSACTION;

    OPEN cursor_produtoras;

    leitura_produtoras: LOOP

        FETCH cursor_produtoras
        INTO v_produtora_id;

        IF v_finalizado THEN
            LEAVE leitura_produtoras;
        END IF;

        SET v_minutos =
            minutos_assistidos_por_produtora(
                v_produtora_id,
                v_competencia
            );

        INSERT INTO faturamento_produtoras (
            produtora_id,
            competencia,
            minutos_consumidos
        )
        VALUES (
            v_produtora_id,
            v_competencia,
            v_minutos
        )
        ON DUPLICATE KEY UPDATE
            minutos_consumidos = v_minutos,
            atualizado_em = CURRENT_TIMESTAMP;

    END LOOP;

    CLOSE cursor_produtoras;

    COMMIT;
END$$

CREATE TRIGGER trg_assinantes_saldo_insert
BEFORE INSERT ON assinantes
FOR EACH ROW
BEGIN
    IF NEW.saldo < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'O saldo do assinante nao pode ser negativo.';
    END IF;
END$$

CREATE TRIGGER trg_assinantes_saldo_update
BEFORE UPDATE ON assinantes
FOR EACH ROW
BEGIN
    IF NEW.saldo < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'O saldo do assinante nao pode ser negativo.';
    END IF;
END$$

CREATE TRIGGER trg_assinantes_normalizar_insert
BEFORE INSERT ON assinantes
FOR EACH ROW
BEGIN
    SET NEW.nome_real =
        UPPER(TRIM(NEW.nome_real));
END$$

CREATE TRIGGER trg_assinantes_manutencao_update
BEFORE UPDATE ON assinantes
FOR EACH ROW
BEGIN
    SET NEW.nome_real =
        UPPER(TRIM(NEW.nome_real));

    SET NEW.data_ultima_alteracao =
        CURRENT_TIMESTAMP;
END$$

CREATE TRIGGER trg_limite_perfis_insert
BEFORE INSERT ON perfis
FOR EACH ROW
BEGIN
    IF NEW.ativo = TRUE
       AND (
            SELECT COUNT(*)
            FROM perfis
            WHERE assinante_id = NEW.assinante_id
              AND ativo = TRUE
       ) >= 5 THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Um assinante pode ter no maximo 5 perfis ativos.';
    END IF;
END$$

CREATE TRIGGER trg_limite_perfis_update
BEFORE UPDATE ON perfis
FOR EACH ROW
BEGIN
    IF NEW.ativo = TRUE
       AND (
            OLD.ativo = FALSE
            OR OLD.assinante_id <> NEW.assinante_id
       )
       AND (
            SELECT COUNT(*)
            FROM perfis
            WHERE assinante_id = NEW.assinante_id
              AND ativo = TRUE
       ) >= 5 THEN

        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
            'Um assinante pode ter no maximo 5 perfis ativos.';
    END IF;
END$$

CREATE TRIGGER trg_bloqueia_update_reproducoes
BEFORE UPDATE ON reproducoes
FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT =
        'Logs de reproducao sao imutaveis.';
END$$

CREATE TRIGGER trg_bloqueia_delete_reproducoes
BEFORE DELETE ON reproducoes
FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT =
        'Logs de reproducao nao podem ser apagados.';
END$$

CREATE TRIGGER trg_bloqueia_delete_videos
BEFORE DELETE ON videos
FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT =
        'Altere o status do video para removido.';
END$$

CREATE TRIGGER trg_auditoria_perfis_update
AFTER UPDATE ON perfis
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_log (
        tabela,
        operacao,
        usuario,
        valor_antigo,
        valor_novo
    )
    VALUES (
        'perfis',
        'UPDATE',
        CURRENT_USER(),
        JSON_OBJECT(
            'id', OLD.id,
            'assinante_id', OLD.assinante_id,
            'nome_exibicao', OLD.nome_exibicao,
            'ativo', OLD.ativo,
            'preferencias', OLD.preferencias
        ),
        JSON_OBJECT(
            'id', NEW.id,
            'assinante_id', NEW.assinante_id,
            'nome_exibicao', NEW.nome_exibicao,
            'ativo', NEW.ativo,
            'preferencias', NEW.preferencias
        )
    );
END$$

DELIMITER ;

CREATE OR REPLACE VIEW vw_engajamento_analistas AS
SELECT
    SHA2(
        CAST(a.id AS CHAR),
        256
    ) AS assinante_hash,
    calcular_idade(a.data_nascimento) AS idade,
    a.uf,
    r.dispositivo,
    COUNT(r.id) AS total_eventos,
    ROUND(
        SUM(r.tempo_consumido_segundos) / 60,
        2
    ) AS minutos_consumidos,
    MAX(r.registrado_em) AS ultimo_acesso
FROM assinantes a
INNER JOIN perfis p
    ON p.assinante_id = a.id
INNER JOIN reproducoes r
    ON r.perfil_id = p.id
GROUP BY
    a.id,
    a.data_nascimento,
    a.uf,
    r.dispositivo;

CREATE ROLE IF NOT EXISTS
    'role_streamflow_app',
    'role_streamflow_auditor',
    'role_streamflow_marketing';

CREATE USER IF NOT EXISTS
    'streamflow_app'@'%'
IDENTIFIED BY 'sla1234';

CREATE USER IF NOT EXISTS
    'streamflow_auditor'@'%'
IDENTIFIED BY 'sla1234';

CREATE USER IF NOT EXISTS
    'streamflow_marketing'@'%'
IDENTIFIED BY 'sla1234';

GRANT
    'role_streamflow_app'
TO
    'streamflow_app'@'%';

GRANT
    'role_streamflow_auditor'
TO
    'streamflow_auditor'@'%';

GRANT
    'role_streamflow_marketing'
TO
    'streamflow_marketing'@'%';

GRANT SELECT
ON streamflow.planos
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.assinantes
TO 'role_streamflow_app';

GRANT INSERT (
    nome_real,
    email,
    cpf,
    data_nascimento,
    uf,
    token_pagamento
)
ON streamflow.assinantes
TO 'role_streamflow_app';

GRANT UPDATE (
    nome_real,
    email,
    data_nascimento,
    uf,
    token_pagamento
)
ON streamflow.assinantes
TO 'role_streamflow_app';

GRANT SELECT, INSERT, UPDATE
ON streamflow.perfis
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.produtoras
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.series
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.temporadas
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.videos
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.filmes
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.episodios
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.categorias
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.videos_categorias
TO 'role_streamflow_app';

GRANT EXECUTE
ON PROCEDURE streamflow.registrar_reproducao
TO 'role_streamflow_app';

GRANT EXECUTE
ON PROCEDURE streamflow.realizar_cobranca_mensal
TO 'role_streamflow_app';

GRANT SELECT
ON streamflow.reproducoes
TO 'role_streamflow_auditor';

GRANT SELECT
ON streamflow.auditoria_log
TO 'role_streamflow_auditor';

GRANT SELECT
ON streamflow.videos
TO 'role_streamflow_auditor';

GRANT SELECT
ON streamflow.produtoras
TO 'role_streamflow_auditor';

GRANT SELECT
ON streamflow.faturamento_produtoras
TO 'role_streamflow_auditor';

GRANT EXECUTE
ON PROCEDURE streamflow.gerar_faturamento_mensal
TO 'role_streamflow_auditor';

GRANT SELECT
ON streamflow.vw_engajamento_analistas
TO 'role_streamflow_marketing';

SET DEFAULT ROLE
    'role_streamflow_app'
TO
    'streamflow_app'@'%';

SET DEFAULT ROLE
    'role_streamflow_auditor'
TO
    'streamflow_auditor'@'%';

SET DEFAULT ROLE
    'role_streamflow_marketing'
TO
    'streamflow_marketing'@'%';