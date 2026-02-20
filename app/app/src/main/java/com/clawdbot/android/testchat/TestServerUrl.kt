package com.clawdbot.android.testchat

private const val officialHost = "vimagram.vimalinx.xyz"
private const val legacyHost = "123.60.21.129:8788"
private const val cloudHost = "49.235.88.239"
private const val defaultPort = "18788"

fun normalizeServerBaseUrl(raw: String): String {
  val trimmed = raw.trim().removeSuffix("/")
  if (trimmed.isBlank()) return trimmed
  val normalized = normalizeOfficialHost(trimmed)
  if (normalized.startsWith("http://") || normalized.startsWith("https://")) {
    return normalized
  }
  val withPort = ensureDefaultPortForBareIp(normalized)
  val scheme = if (isLikelyLocalHost(withPort) || isBareIpv4Host(withPort)) "http://" else "https://"
  return scheme + withPort
}

private fun normalizeOfficialHost(raw: String): String {
  val withoutScheme =
    raw.removePrefix("http://")
      .removePrefix("https://")
  if (
    withoutScheme.equals(officialHost, ignoreCase = true) ||
      withoutScheme.equals("$officialHost:$defaultPort", ignoreCase = true) ||
      withoutScheme.equals(legacyHost, ignoreCase = true)
  ) {
    return "https://$officialHost"
  }
  if (withoutScheme.equals(cloudHost, ignoreCase = true)) {
    return "http://$cloudHost:$defaultPort"
  }
  return raw
}

private fun ensureDefaultPortForBareIp(host: String): String {
  val base = host.substringBefore("/")
  if (!isIpv4Address(base)) return host
  if (base.contains(":")) return host
  val suffix = host.removePrefix(base)
  return "$base:$defaultPort$suffix"
}

private fun isBareIpv4Host(host: String): Boolean {
  val base = host.substringBefore("/").substringBefore(":")
  return isIpv4Address(base)
}

private fun isIpv4Address(value: String): Boolean {
  val parts = value.split(".")
  if (parts.size != 4) return false
  return parts.all { part ->
    val number = part.toIntOrNull() ?: return@all false
    number in 0..255
  }
}

private fun isLikelyLocalHost(host: String): Boolean {
  val lowered = host.lowercase()
  val base = lowered.substringBefore("/").substringBefore(":")
  if (base == "localhost" || base == "127.0.0.1") return true
  if (base.startsWith("10.") || base.startsWith("192.168.")) return true
  if (base.startsWith("172.")) {
    val second = base.split(".").getOrNull(1)?.toIntOrNull()
    if (second != null && second in 16..31) return true
  }
  return false
}
