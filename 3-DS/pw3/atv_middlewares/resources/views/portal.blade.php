<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Portal</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            padding: 50px;
            max-width: 600px;
            width: 100%;
        }

        .header {
            text-align: center;
            margin-bottom: 40px;
        }

        .header h1 {
            color: #333;
            font-size: 32px;
            margin-bottom: 10px;
        }

        .header p {
            color: #666;
            font-size: 14px;
        }

        .mensagens {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .mensagem {
            padding: 20px;
            border-radius: 8px;
            border-left: 4px solid;
            font-size: 16px;
            line-height: 1.6;
        }

        .mensagem.bemvindo {
            background-color: #e8f5e9;
            border-color: #4caf50;
            color: #2e7d32;
        }

        .mensagem.naoautorizado {
            background-color: #fff3e0;
            border-color: #ff9800;
            color: #e65100;
        }

        .mensagem.contato {
            background-color: #e3f2fd;
            border-color: #2196f3;
            color: #1565c0;
        }

        .footer {
            text-align: center;
            margin-top: 40px;
            padding-top: 20px;
            border-top: 1px solid #eee;
            color: #999;
            font-size: 12px;
        }

        .footer a {
            color: #667eea;
            text-decoration: none;
        }

        .footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🌐 Portal</h1>
            <p>Informações de Acesso</p>
        </div>

        <div class="mensagens">
            @if(isset($mensagens))
                <div class="mensagem bemvindo">
                    ✅ {{ $mensagens['bemvindo'] }}
                </div>

                <div class="mensagem naoautorizado">
                    ⚠️ {{ $mensagens['naoautorizado'] }}
                </div>

                <div class="mensagem contato">
                    ℹ️ {{ $mensagens['contato'] }}
                </div>
            @else
                <div class="mensagem naoautorizado">
                    ⚠️ Nenhuma informação disponível
                </div>
            @endif
        </div>

        <div class="footer">
            <p>Desenvolvido como atividade acadêmica de Middlewares em Laravel</p>
        </div>
    </div>
</body>
</html>
