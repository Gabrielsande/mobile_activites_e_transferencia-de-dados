# Jogo de Adivinhação

Aplicativo Android em Kotlin com duas telas, feito na **Aula 15 de Mobile: Navegação entre Activities e Transferência de Dados**.

O jogador informa o nome e escolhe um limite (10, 50 ou 100). O app sorteia um número secreto e dá dicas de "Quente" ou "Frio" a cada palpite, até o jogador acertar.

## Como funciona

1. **Tela inicial (`MainActivity`)**: o jogador digita o nome e escolhe o limite. Ao tocar em **JOGAR**, o app envia os dois dados para a segunda tela e a abre.
2. **Tela do jogo (`JogoActivity`)**: recebe o nome e o limite, mostra as boas-vindas e sorteia o número secreto. A cada palpite, mostra a dica e conta as tentativas.
3. **Ao acertar**: aparece a mensagem "Ana acertou em 4 tentativas!" e o botão **Jogar de novo**, que fecha a tela do jogo e volta para a primeira.

### Regras da dica

| Situação | Dica |
| --- | --- |
| Palpite igual ao número secreto | **Acertou!** |
| Diferença de até 10% do limite | **Quente** |
| Diferença maior que 10% do limite | **Frio** |

Quente e Frio também informam se o número secreto é **MAIOR** ou **MENOR** que o palpite.

Exemplo: com limite 50, a dica é "Quente" quando a diferença for de até 5.

## Conceitos praticados

- Criar uma segunda Activity e registrá-la no `AndroidManifest.xml`.
- Abrir uma nova tela com `startActivity(Intent(this, JogoActivity::class.java))`.
- Enviar dados entre telas com `intent.putExtra("nome", nome)` e `intent.putExtra("maximo", maximo)`.
- Receber os dados com `intent.getStringExtra("nome")` e `intent.getIntExtra("maximo", 50)`.
- Voltar para a tela anterior com `finish()`, sem empilhar uma nova `MainActivity`.
- Validar os campos de texto antes de usar (`isEmpty()` e `toIntOrNull()`), evitando que o app feche por entrada inválida.

## Estrutura do projeto

```
app/src/main/
├── AndroidManifest.xml
├── java/com/example/aulatelas/
│   ├── MainActivity.kt
│   └── JogoActivity.kt
└── res/layout/
    ├── activity_main.xml
    └── activity_jogo.xml
```

## Como executar

1. Clone ou baixe o projeto.
2. Abra no **Android Studio**.
3. Aguarde o **Gradle Sync** terminar.
4. Escolha um emulador ou dispositivo e clique em **Run**.

## Possíveis problemas

**Erro "AAR metadata" com `androidx.core:core:1.19.0`**
Essa versão da biblioteca exige o Android Gradle Plugin 9.1.0 e o `compileSdk` 37. Para continuar com o AGP 8.13.2, abra o `libs.versions.toml`, troque `coreKtx = "1.17.0"` e clique em **Sync Now**.

**O app fecha ao tocar em JOGAR**
Confira se a `JogoActivity` está declarada dentro de `<application>` no `AndroidManifest.xml`:

```xml
<activity
    android:name=".JogoActivity"
    android:exported="false" />
```

## Tecnologias

- Kotlin
- Android Studio
- Views com XML (`LinearLayout`, `RadioGroup`, `EditText`, `Button`)
