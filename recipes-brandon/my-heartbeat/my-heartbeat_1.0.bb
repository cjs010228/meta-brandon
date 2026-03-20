SUMMARY = "My Heartbeat Daemon"
DESCRIPTION = "A practice daemon for OpenBMC"
LICENSE = "CLOSED"

inherit cmake systemd pkgconfig

DEPENDS += "boost sdbusplus"

# 修改後的寫法
SRC_URI = "git://github.com/cjs010228/meta-brandon.git;protocol=https;branch=main"

# 這行現在才會真正發揮作用
#SRCREV = "e4e07dacf5aa264a483b4ca330469a748f894436"
SRCREV = "${AUTOREV}"

# 注意：當使用 Git 時，S 通常要指向 git 資料夾
S = "${WORKDIR}/git"

SYSTEMD_SERVICE:${PN} = "my-heartbeat.service"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    
    # === [修正] 改成從 ${S} 複製，不是 ${WORKDIR} ===
    install -m 0644 ${S}/my-heartbeat.service ${D}${systemd_system_unitdir}
}