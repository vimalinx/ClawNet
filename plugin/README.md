# Vimalinx Server plugin (Clawdbot)

English | [中文](README.zh.md)

Channel plugin that connects Clawdbot to a Vimalinx Server. Supports poll or
webhook inbound modes and token-based auth.

## Requirements

- Clawdbot Gateway running (Node 22+).
- Vimalinx Server reachable at a base URL.

## Install

From a local checkout:

```bash
clawdbot plugins install ./plugin
```

For dev (no copy, live link):

```bash
clawdbot plugins install -l ./plugin
```

If the plugin shows as disabled:

```bash
clawdbot plugins enable test
```

## Configure (wizard)

```bash
clawdbot onboard
```

The wizard will:
- Ask for the Vimalinx server (official or custom URL).
- Prompt for the token (from Vimagram).
- Let you choose inbound mode (poll or webhook).

## Configure (manual)

Minimal config (single account):

```yaml
channels:
  test:
    baseUrl: http://server-host:8788
    userId: user-id
    token: host-token
    inboundMode: poll
```

Optional fields:
- `webhookPath` (default `/test-webhook`)
- `webhookToken` (defaults to `token`)

## Verify

```bash
clawdbot channels status --probe
```

If everything is healthy, the channel should show as connected/polling.

## Related

- Server: `server/README.md`
- Android app: `app/README.md`
