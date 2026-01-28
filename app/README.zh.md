## Vimagram（Android）

中文 | [English](README.md)

Vimalinx Server 的 Android 客户端，用于注册/登录、生成主机 Token、聊天。

特点：
- 直接连接 Vimalinx Server（不经过 Gateway）。
- 账号页展示已连接主机 Token，方便恢复。
- 支持语言切换（系统/中文/English）。

## 用 Android Studio 打开
- 打开 `app` 目录。

## 构建 / 运行

```bash
cd app
./gradlew :app:assembleDebug
./gradlew :app:installDebug
./gradlew :app:testDebugUnitTest
```

Release 构建：

```bash
./gradlew :app:assembleRelease
```

若未设置 `ANDROID_SDK_ROOT` / `ANDROID_HOME`，`gradlew` 会默认寻找
`~/Library/Android/sdk`（macOS 默认路径）。

## 连接 Vimalinx Server

1) 启动服务器（见 `server/README.md`）。
2) 在 Vimagram 中输入服务器地址，注册或登录。
3) 在 **Account** 里生成主机 Token 并复制。
4) 在 Clawdbot 中安装并配置 Vimalinx Server 插件
   （见 `plugin/README.md`），粘贴 Token。

## 备注

- Debug 包可用 `:app:installDebug` 直接安装。
- 设备弹出安装权限时，请允许继续安装。
