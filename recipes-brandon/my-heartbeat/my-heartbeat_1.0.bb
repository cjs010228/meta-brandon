SUMMARY = "My Heartbeat Daemon"
DESCRIPTION = "A practice daemon for OpenBMC"
LICENSE = "CLOSED"

inherit meson pkgconfig systemd

DEPENDS += "boost sdbusplus"

# 1. 指向你新的 App Repo (雲端) + 你的 .service 檔 (本地)
SRC_URI = "git://github.com/cjs010228/my-heartbeat-app.git;protocol=https;branch=main \
           file://my-heartbeat.service"

SRCREV = "${AUTOREV}"

# 2. 告訴 Meson，抓下來的 Git 原始碼在工作區的 git/ 目錄下
S = "${WORKDIR}/git"

# 3. 傳遞功能開關給 App
EXTRA_OEMESON:append = " -Denable-debug-log=true"

# 4. Systemd 服務設定
SYSTEMD_SERVICE:${PN} = "my-heartbeat.service"

# 5. 安裝階段：Meson 會自動幫你安裝執行檔，我們這裡只需要手動安裝 .service 檔
do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    
    # 注意：.service 檔是 file:// 來的，所以它會放在 ${UNPACKDIR} (或 ${WORKDIR})
    install -m 0644 ${UNPACKDIR}/my-heartbeat.service ${D}${systemd_system_unitdir}/
}