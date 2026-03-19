SUMMARY = "My Heartbeat Daemon"
DESCRIPTION = "A practice daemon for OpenBMC"
LICENSE = "CLOSED"

inherit cmake systemd pkgconfig

DEPENDS += "boost sdbusplus"

# 所有檔案指定 subdir=sources
SRC_URI = "file://main.cpp \
           file://CMakeLists.txt \
           file://my-heartbeat.service \
           file://LICENSE"

S = "${UNPACKDIR}"

SYSTEMD_SERVICE:${PN} = "my-heartbeat.service"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    
    # === [修正] 改成從 ${S} 複製，不是 ${WORKDIR} ===
    install -m 0644 ${S}/my-heartbeat.service ${D}${systemd_system_unitdir}
}