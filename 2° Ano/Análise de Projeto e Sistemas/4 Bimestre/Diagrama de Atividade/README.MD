Objetivo do Diagrama de Atividade — VetMate

O diagrama de atividade representa de forma visual o fluxo de ações e decisões que ocorrem dentro do sistema VetMate, desde o momento em que o tutor (usuário) acessa a plataforma até o encerramento da solicitação de atendimento na clínica.

Ele tem como objetivo descrever o comportamento dinâmico do sistema, mostrando como as atividades se encadeiam entre o usuário (Tutor), o Sistema VetMate e a Clínica, evidenciando também as condições de decisão e os fluxos paralelos que podem ocorrer.


Parte do Sistema Representada

O diagrama foca na etapa de agendamento de serviços veterinários, abrangendo:

Ações do Tutor: login, cadastro, seleção de serviço e envio dos dados do pet.

Ações do Sistema: validação das informações, busca de clínicas compatíveis, verificação de disponibilidade e confirmação do agendamento.

Ações da Clínica: execução do atendimento e encerramento da solicitação.

Além disso, há um processo paralelo (fork/join) que envia notificações ao tutor e registra logs de auditoria, garantindo rastreabilidade e comunicação eficiente.
