cmake_minimum_required(VERSION 3.4.1)

project(sqlcipherso)

#set(BUILD_SHARED_LIBS ON)

#设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../libs/${ANDROID_ABI}/)

# 设置 C++ 编译选项，参数之间用空格分隔
#[[add_definitions(-DSQLITE_HAS_CODEC -DSQLITE_SOUNDEX -DHAVE_USLEEP=1
    -DSQLITE_MAX_VARIABLE_NUMBER=99999 -DSQLITE_TEMP_STORE=3 -DSQLITE_THREADSAFE=1
    -DSQLITE_DEFAULT_JOURNAL_SIZE_LIMIT=1048576 -DNDEBUG=1 -DSQLITE_ENABLE_MEMORY_MANAGEMENT=1
     -DSQLITE_ENABLE_LOAD_EXTENSION -DSQLITE_ENABLE_COLUMN_METADATA
     -DSQLITE_ENABLE_UNLOCK_NOTIFY -DSQLITE_ENABLE_RTREE -DSQLITE_ENABLE_STAT3
     -DSQLITE_ENABLE_STAT4 -DSQLITE_ENABLE_JSON1 -DSQLITE_ENABLE_FTS3_PARENTHESIS
     -DSQLITE_ENABLE_FTS4 -DSQLITE_ENABLE_FTS5 -DSQLCIPHER_CRYPTO_OPENSSL -DSQLITE_ENABLE_DBSTAT_VTAB)]]



#设置.h文件对应的路径
set(CIPHER_HEADER ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/)
#包含头文件
include_directories( ${CIPHER_HEADER} )

#包含cpp文件
file(GLOB CIPHER_SRCS ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/*.cpp)


#生成可执行文件
#add_executable(${PROJECT_NAME} ${CIPHER_SRCS})

#生成动态库
add_library(${PROJECT_NAME} SHARED ${CIPHER_SRCS})



#添加静态库
#[[set(CYPTO "${CMAKE_CURRENT_SOURCE_DIR}/src/main/external/libcrypto.a")
set(SSL "${CMAKE_CURRENT_SOURCE_DIR}/src/main/external/libssl.a")]]

#链接静态库
#[[target_link_libraries(${PROJECT_NAME}
        ${CYPTO}
        ${SSL}
)]]

find_library(LOG-LIB log)
#find_library(ATOMIC-LIB atomic)

target_link_libraries(${PROJECT_NAME} ${LOG-LIB})

#add_executable(demo demo.cpp) # 生成可执行文件
#add_library(common STATIC util.cpp) # 生成静态库
#add_library(common SHARED util.cpp) # 生成动态库或共享库