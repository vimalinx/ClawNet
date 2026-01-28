# Vimalinx Suite

中文 | [English](README.md)

Vimalinx Suite 是 Vimalinx Server 通道的全栈实现：
- Server：`server`
- Plugin：`plugin`
- Android App（Vimagram）：`app`

## 快速启动（本地）

安装依赖：

```bash
pnpm install
pnpm build
```

启动 Vimalinx Server：

```bash
export TEST_SERVER_PORT=8788
export TEST_USERS_FILE=/path/to/vimalinx-users.json
export TEST_ALLOW_REGISTRATION=true

node server/server.mjs
```

启动 Gateway：

```bash
pnpm clawdbot gateway --port 18789 --verbose
```

安装并配置插件：

```bash
pnpm clawdbot plugins install ./plugin
pnpm clawdbot onboard
```

验证：

```bash
clawdbot channels status --probe
```

## Android App（Vimagram）

```bash
cd app
./gradlew :app:installDebug
```

登录服务器后，在 **Account** 生成主机 Token，并在插件向导里粘贴。

## 文档

- Server：`server/README.md`
- Plugin：`plugin/README.md`
- Android App：`app/README.md`
