FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://my_mock_board.json"

do_install:append() {
    install -d ${D}${datadir}/entity-manager/configurations
    install -m 0444 ${UNPACKDIR}/my_mock_board.json ${D}${datadir}/entity-manager/configurations/
}