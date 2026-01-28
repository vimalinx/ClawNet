# Vimalinx Suite

English | [中文](README.zh.md)

Vimalinx Suite is a full stack for the Vimalinx Server channel:
- Server: `server`
- Plugin: `plugin`
- Android app (Vimagram): `app`

## Quickstart (local)

Install deps:

```bash
pnpm install
pnpm build
```

Start the Vimalinx Server:

```bash
export TEST_SERVER_PORT=8788
export TEST_USERS_FILE=/path/to/vimalinx-users.json
export TEST_ALLOW_REGISTRATION=true

node server/server.mjs
```

Start the Gateway:

```bash
pnpm clawdbot gateway --port 18789 --verbose
```

Install + configure the plugin:

```bash
pnpm clawdbot plugins install ./plugin
pnpm clawdbot onboard
```

Verify:

```bash
clawdbot channels status --probe
```

## Android app (Vimagram)

```bash
cd app
./gradlew :app:installDebug
```

Then log in to the server, generate a host token in **Account**, and paste it in the
plugin onboarding flow.

## Docs

- Server: `server/README.md`
- Plugin: `plugin/README.md`
- Android app: `app/README.md`
