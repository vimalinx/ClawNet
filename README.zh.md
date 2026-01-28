# 🦞 Clawdbot — 个人 AI 助手

<p align="center">
  <img src="https://raw.githubusercontent.com/clawdbot/clawdbot/main/docs/whatsapp-clawd.jpg" alt="Clawdbot" width="400">
</p>

<p align="center">
  <strong>EXFOLIATE! EXFOLIATE!</strong>
</p>

<p align="center">
  <a href="https://github.com/clawdbot/clawdbot/actions/workflows/ci.yml?branch=main"><img src="https://img.shields.io/github/actions/workflow/status/clawdbot/clawdbot/ci.yml?branch=main&style=for-the-badge" alt="CI status"></a>
  <a href="https://github.com/clawdbot/clawdbot/releases"><img src="https://img.shields.io/github/v/release/clawdbot/clawdbot?include_prereleases&style=for-the-badge" alt="GitHub release"></a>
  <a href="https://deepwiki.com/clawdbot/clawdbot"><img src="https://img.shields.io/badge/DeepWiki-clawdbot-111111?style=for-the-badge" alt="DeepWiki"></a>
  <a href="https://discord.gg/clawd"><img src="https://img.shields.io/discord/1456350064065904867?label=Discord&logo=discord&logoColor=white&color=5865F2&style=for-the-badge" alt="Discord"></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge" alt="MIT License"></a>
</p>

中文 | [English](README.md)

**Clawdbot** 是一个运行在你自己设备上的 *个人 AI 助手*。
它能在你已经使用的聊天渠道里回复你（WhatsApp、Telegram、Slack、Discord、Google Chat、Signal、iMessage、Microsoft Teams、WebChat），并支持扩展渠道（BlueBubbles、Matrix、Zalo、Zalo Personal）。它也能在 macOS/iOS/Android 上“说话与倾听”，并渲染你可控的实时 Canvas。Gateway 只是控制面，真正的产品是你的助手。

如果你想要一个单人使用、快、一直在线、感觉像本地的个人助手，这就是它。

[Website](https://clawdbot.com) · [Docs](https://docs.clawd.bot) · [Getting Started](https://docs.clawd.bot/start/getting-started) · [Updating](https://docs.clawd.bot/install/updating) · [Showcase](https://docs.clawd.bot/start/showcase) · [FAQ](https://docs.clawd.bot/start/faq) · [Wizard](https://docs.clawd.bot/start/wizard) · [Nix](https://github.com/clawdbot/nix-clawdbot) · [Docker](https://docs.clawd.bot/install/docker) · [Discord](https://discord.gg/clawd)

推荐路径：运行引导向导（`clawdbot onboard`）。它会带你完成 gateway、workspace、channels、skills 的配置。
CLI 向导是推荐方式，可在 **macOS、Linux、Windows（通过 WSL2，强烈推荐）** 使用。
支持 npm / pnpm / bun。
新安装请从这里开始：[Getting started](https://docs.clawd.bot/start/getting-started)

**订阅（OAuth）：**
- **[Anthropic](https://www.anthropic.com/)**（Claude Pro/Max）
- **[OpenAI](https://openai.com/)**（ChatGPT/Codex）

模型建议：虽然支持任何模型，但强烈推荐 **Anthropic Pro/Max (100/200) + Opus 4.5**，长上下文表现更好，且更抗提示注入。详见 [Onboarding](https://docs.clawd.bot/start/onboarding)。

## 模型（选择 + 认证）

- 模型配置 + CLI： [Models](https://docs.clawd.bot/concepts/models)
- 认证轮换（OAuth vs API keys）+ 兜底： [Model failover](https://docs.clawd.bot/concepts/model-failover)

## 安装（推荐）

运行环境：**Node ≥22**。

```bash
npm install -g clawdbot@latest
# or: pnpm add -g clawdbot@latest

clawdbot onboard --install-daemon
```

向导会安装 Gateway 守护进程（launchd/systemd user service），确保持续运行。

## 快速开始（TL;DR）

运行环境：**Node ≥22**。

完整入门指南（认证、配对、渠道）：[Getting started](https://docs.clawd.bot/start/getting-started)

```bash
clawdbot onboard --install-daemon

clawdbot gateway --port 18789 --verbose

# 发送消息
clawdbot message send --to +1234567890 --message "Hello from Clawdbot"

# 和助手对话（可回传到任意已连接渠道）
clawdbot agent --message "Ship checklist" --thinking high
```

升级？查看 [Updating guide](https://docs.clawd.bot/install/updating)（并运行 `clawdbot doctor`）。

## Vimalinx Server 全栈（服务器 + 插件 + Android App）

本仓库包含 Vimalinx Server 相关的全栈实现：
- Server：`server`
- Plugin：`plugin`
- Android App（Vimagram）：`app`

部署流程（概览）：
1) 启动 Vimalinx Server，并配置用户/邀请码。
2) 安装插件（`clawdbot plugins install ./plugin`），用 `clawdbot onboard` 进行配置。
3) 构建 Vimagram，登录后在 Account 里生成主机 Token。

详细步骤请看各子项目 README。

## Vimalinx 命令行快速启动

适用于从本仓库本地启动 Server + 插件：

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

## 发布通道

- **stable**：打 tag 的正式版（`vYYYY.M.D` 或 `vYYYY.M.D-<patch>`），npm dist-tag `latest`。
- **beta**：预发布（`vYYYY.M.D-beta.N`），npm dist-tag `beta`（可能缺少 macOS app）。
- **dev**：`main` 分支滚动更新，npm dist-tag `dev`。

切换通道（git + npm）：`clawdbot update --channel stable|beta|dev`。
详情： [Development channels](https://docs.clawd.bot/install/development-channels)。

## 从源码运行（开发）

推荐使用 `pnpm` 从源码构建。Bun 可选，用于直接执行 TypeScript。

```bash
git clone https://github.com/clawdbot/clawdbot.git
cd clawdbot

pnpm install
pnpm ui:build # 首次运行会自动安装 UI 依赖
pnpm build

pnpm clawdbot onboard --install-daemon

# Dev loop（TS 变更自动重载）
pnpm gateway:watch
```

说明：`pnpm clawdbot ...` 通过 `tsx` 直接跑 TypeScript。`pnpm build` 会输出 `dist/` 供 Node/打包版 `clawdbot` 使用。

## 安全默认值（DM 访问）

Clawdbot 会连接真实聊天渠道。请将入站 DM 视为 **不可信输入**。

完整安全指南： [Security](https://docs.clawd.bot/gateway/security)

Telegram/WhatsApp/Signal/iMessage/Microsoft Teams/Discord/Google Chat/Slack 默认行为：
- **DM 配对**（`dmPolicy="pairing"` / `channels.discord.dm.policy="pairing"` / `channels.slack.dm.policy="pairing"`）：陌生人会收到配对码，机器人不会处理消息。
- 允许某人：`clawdbot pairing approve <channel> <code>`（会加入本地 allowlist）。
- 公共 DM 需显式开放：设置 `dmPolicy="open"`，并在 allowlist 中加入 `"*"`。

建议运行 `clawdbot doctor` 排查高风险配置。

## 亮点

- **[Local-first Gateway](https://docs.clawd.bot/gateway)** — 单一控制面，管理 sessions、channels、tools、events。
- **[多渠道收件箱](https://docs.clawd.bot/channels)** — WhatsApp/Telegram/Slack/Discord/Google Chat/Signal/iMessage/BlueBubbles/Microsoft Teams/Matrix/Zalo/Zalo Personal/WebChat。
- **[多 Agent 路由](https://docs.clawd.bot/gateway/configuration)** — 将不同渠道/账号/对话路由到隔离的 agent。
- **[Voice Wake](https://docs.clawd.bot/nodes/voicewake) + [Talk Mode](https://docs.clawd.bot/nodes/talk)** — macOS/iOS/Android 语音对话。
- **[Live Canvas](https://docs.clawd.bot/platforms/mac/canvas)** — 代理驱动的视觉工作区（[A2UI](https://docs.clawd.bot/platforms/mac/canvas#canvas-a2ui)）。
- **[一等工具体系](https://docs.clawd.bot/tools)** — browser/canvas/nodes/cron/sessions 等。
- **[配套应用](https://docs.clawd.bot/platforms/macos)** — macOS 菜单栏 app + iOS/Android [nodes](https://docs.clawd.bot/nodes)。
- **[Onboarding](https://docs.clawd.bot/start/wizard) + [skills](https://docs.clawd.bot/tools/skills)** — 向导式安装与技能管理。

## Star 历史

[![Star History Chart](https://api.star-history.com/svg?repos=clawdbot/clawdbot&type=date&legend=top-left)](https://www.star-history.com/#clawdbot/clawdbot&type=date&legend=top-left)

## 我们已经构建的东西

### 核心平台
- [Gateway WS 控制面](https://docs.clawd.bot/gateway)：会话、presence、配置、cron、webhooks、[Control UI](https://docs.clawd.bot/web)、[Canvas host](https://docs.clawd.bot/platforms/mac/canvas#canvas-a2ui)。
- [CLI 入口](https://docs.clawd.bot/tools/agent-send)：gateway、agent、send、[wizard](https://docs.clawd.bot/start/wizard)、[doctor](https://docs.clawd.bot/gateway/doctor)。
- [Pi agent 运行时](https://docs.clawd.bot/concepts/agent)：RPC 模式，支持工具流和块流。
- [会话模型](https://docs.clawd.bot/concepts/session)：`main` 统一会话、群组隔离、激活/队列模式、回传。群聊规则： [Groups](https://docs.clawd.bot/concepts/groups)。
- [媒体管线](https://docs.clawd.bot/nodes/images)：图片/音频/视频、转写、尺寸限制、临时文件生命周期。音频细节： [Audio](https://docs.clawd.bot/nodes/audio)。

### 渠道
- [Channels](https://docs.clawd.bot/channels)：[WhatsApp](https://docs.clawd.bot/channels/whatsapp)（Baileys）、[Telegram](https://docs.clawd.bot/channels/telegram)（grammY）、[Slack](https://docs.clawd.bot/channels/slack)（Bolt）、[Discord](https://docs.clawd.bot/channels/discord)（discord.js）、[Google Chat](https://docs.clawd.bot/channels/googlechat)（Chat API）、[Signal](https://docs.clawd.bot/channels/signal)（signal-cli）、[iMessage](https://docs.clawd.bot/channels/imessage)（imsg）、[BlueBubbles](https://docs.clawd.bot/channels/bluebubbles)、[Microsoft Teams](https://docs.clawd.bot/channels/msteams)、[Matrix](https://docs.clawd.bot/channels/matrix)、[Zalo](https://docs.clawd.bot/channels/zalo)、[Zalo Personal](https://docs.clawd.bot/channels/zalouser)、[WebChat](https://docs.clawd.bot/web/webchat)。
- [群聊路由](https://docs.clawd.bot/concepts/group-messages)：提及门禁、回复标签、分片与路由。渠道规则： [Channels](https://docs.clawd.bot/channels)。

### Apps + nodes
- [macOS app](https://docs.clawd.bot/platforms/macos)：菜单栏控制面、[Voice Wake](https://docs.clawd.bot/nodes/voicewake)/PTT、[Talk Mode](https://docs.clawd.bot/nodes/talk) overlay、[WebChat](https://docs.clawd.bot/web/webchat)、调试工具、[远程 Gateway](https://docs.clawd.bot/gateway/remote)。
- [iOS node](https://docs.clawd.bot/platforms/ios)：[Canvas](https://docs.clawd.bot/platforms/mac/canvas)、[Voice Wake](https://docs.clawd.bot/nodes/voicewake)、[Talk Mode](https://docs.clawd.bot/nodes/talk)、摄像头、屏幕录制、Bonjour 配对。
- [Android node](https://docs.clawd.bot/platforms/android)：[Canvas](https://docs.clawd.bot/platforms/mac/canvas)、[Talk Mode](https://docs.clawd.bot/nodes/talk)、摄像头、屏幕录制、可选短信。
- [macOS node 模式](https://docs.clawd.bot/nodes)：system.run/system.notify + canvas/camera。

### 工具 + 自动化
- [Browser control](https://docs.clawd.bot/tools/browser)：专用 clawd Chrome/Chromium、快照、动作、上传、profile。
- [Canvas](https://docs.clawd.bot/platforms/mac/canvas)：[A2UI](https://docs.clawd.bot/platforms/mac/canvas#canvas-a2ui) 推送/重置、eval、snapshot。
- [Nodes](https://docs.clawd.bot/nodes)：拍照/录制、屏幕录制、[location.get](https://docs.clawd.bot/nodes/location-command)、通知。
- [Cron + wakeups](https://docs.clawd.bot/automation/cron-jobs)；[webhooks](https://docs.clawd.bot/automation/webhook)；[Gmail Pub/Sub](https://docs.clawd.bot/automation/gmail-pubsub)。
- [Skills 平台](https://docs.clawd.bot/tools/skills)：bundled/managed/workspace skills，带安装门禁 + UI。

### 运行时 + 安全
- [Channel routing](https://docs.clawd.bot/concepts/channel-routing)、[retry policy](https://docs.clawd.bot/concepts/retry)、[streaming/chunking](https://docs.clawd.bot/concepts/streaming)。
- [Presence](https://docs.clawd.bot/concepts/presence)、[typing indicators](https://docs.clawd.bot/concepts/typing-indicators)、[usage tracking](https://docs.clawd.bot/concepts/usage-tracking)。
- [Models](https://docs.clawd.bot/concepts/models)、[model failover](https://docs.clawd.bot/concepts/model-failover)、[session pruning](https://docs.clawd.bot/concepts/session-pruning)。
- [Security](https://docs.clawd.bot/gateway/security) 与 [troubleshooting](https://docs.clawd.bot/channels/troubleshooting)。

### 运维 + 打包
- [Control UI](https://docs.clawd.bot/web) + [WebChat](https://docs.clawd.bot/web/webchat) 直接由 Gateway 提供。
- [Tailscale Serve/Funnel](https://docs.clawd.bot/gateway/tailscale) 或 [SSH tunnels](https://docs.clawd.bot/gateway/remote) 暴露服务。
- [Nix 模式](https://docs.clawd.bot/install/nix) 声明式配置；[Docker](https://docs.clawd.bot/install/docker) 运行。
- [Doctor](https://docs.clawd.bot/gateway/doctor) 迁移与 [logging](https://docs.clawd.bot/logging)。

## 原理（简版）

```
WhatsApp / Telegram / Slack / Discord / Google Chat / Signal / iMessage / BlueBubbles / Microsoft Teams / Matrix / Zalo / Zalo Personal / WebChat
               │
               ▼
┌───────────────────────────────┐
│            Gateway            │
│       (control plane)         │
│     ws://127.0.0.1:18789      │
└──────────────┬────────────────┘
               │
               ├─ Pi agent (RPC)
               ├─ CLI (clawdbot …)
               ├─ WebChat UI
               ├─ macOS app
               └─ iOS / Android nodes
```

## 关键子系统

- **[Gateway WebSocket 网络](https://docs.clawd.bot/concepts/architecture)**：单一 WS 控制面，客户端/工具/事件统一。
- **[Tailscale 暴露](https://docs.clawd.bot/gateway/tailscale)**：Serve/Funnel 对外提供 Dashboard + WS。
- **[Browser control](https://docs.clawd.bot/tools/browser)**：clawd 管理的 Chrome/Chromium + CDP。
- **[Canvas + A2UI](https://docs.clawd.bot/platforms/mac/canvas)**：代理驱动视觉工作区。
- **[Voice Wake](https://docs.clawd.bot/nodes/voicewake) + [Talk Mode](https://docs.clawd.bot/nodes/talk)**：持续语音交互。
- **[Nodes](https://docs.clawd.bot/nodes)**：camera/screen/location/通知，macOS 支持 `system.run`。

## Tailscale 访问（Gateway Dashboard）

Clawdbot 可自动配置 Tailscale **Serve**（tailnet 内）或 **Funnel**（公网）。
配置 `gateway.tailscale.mode`：

- `off`：关闭（默认）
- `serve`：tailnet 内 HTTPS（使用 Tailscale 身份）
- `funnel`：公网 HTTPS（需密码认证）

注意：
- 开启 Serve/Funnel 时，`gateway.bind` 必须保持 `loopback`。
- Serve 可通过 `gateway.auth.mode: "password"` 或 `gateway.auth.allowTailscale: false` 强制密码。
- Funnel 要求 `gateway.auth.mode: "password"`。
- 可用 `gateway.tailscale.resetOnExit` 退出时自动清理。

详情：[Tailscale guide](https://docs.clawd.bot/gateway/tailscale) · [Web surfaces](https://docs.clawd.bot/web)

## 远程 Gateway（Linux 很合适）

Gateway 放在小型 Linux 实例上完全没问题。客户端（macOS app / CLI / WebChat）可通过
**Tailscale Serve/Funnel** 或 **SSH tunnel** 连接，同时仍可配对设备节点（macOS/iOS/Android）
执行本地动作。

- **Gateway 主机** 默认执行 exec 与渠道连接。
- **设备节点** 执行设备本地动作（system.run、camera、screen、notifications）。

总结：exec 在 Gateway 机上跑，设备动作在设备机上跑。
