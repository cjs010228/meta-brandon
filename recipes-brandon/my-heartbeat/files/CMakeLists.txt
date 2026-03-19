cmake_minimum_required(VERSION 3.5)
project(my-heartbeat CXX)

set(CMAKE_CXX_STANDARD 23)

find_package(PkgConfig REQUIRED)
pkg_check_modules(SDBUSPLUS REQUIRED sdbusplus)
find_package(Boost REQUIRED)

# === [修正] 去掉 src/，因為檔案在根目錄 ===
add_executable(my-heartbeat main.cpp)

target_include_directories(my-heartbeat PUBLIC ${SDBUSPLUS_INCLUDE_DIRS})
target_link_libraries(my-heartbeat ${SDBUSPLUS_LIBRARIES} Boost::boost)

install(TARGETS my-heartbeat DESTINATION bin)