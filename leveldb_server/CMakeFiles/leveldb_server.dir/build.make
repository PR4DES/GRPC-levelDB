# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.10

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/qwe52165/GRPC-levelDB/leveldb_server

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/qwe52165/GRPC-levelDB/leveldb_server

# Include any dependencies generated for this target.
include CMakeFiles/leveldb_server.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/leveldb_server.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/leveldb_server.dir/flags.make

leveldb_ycsb.pb.cc: /home/qwe52165/GRPC-levelDB/protos/leveldb_ycsb.proto
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/home/qwe52165/GRPC-levelDB/leveldb_server/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Generating leveldb_ycsb.pb.cc, leveldb_ycsb.pb.h, leveldb_ycsb.grpc.pb.cc, leveldb_ycsb.grpc.pb.h"
	grpc/third_party/protobuf/protoc --grpc_out /home/qwe52165/GRPC-levelDB/leveldb_server --cpp_out /home/qwe52165/GRPC-levelDB/leveldb_server -I /home/qwe52165/GRPC-levelDB/protos --plugin=protoc-gen-grpc="/home/qwe52165/GRPC-levelDB/leveldb_server/grpc/grpc_cpp_plugin" /home/qwe52165/GRPC-levelDB/protos/leveldb_ycsb.proto

leveldb_ycsb.pb.h: leveldb_ycsb.pb.cc
	@$(CMAKE_COMMAND) -E touch_nocreate leveldb_ycsb.pb.h

leveldb_ycsb.grpc.pb.cc: leveldb_ycsb.pb.cc
	@$(CMAKE_COMMAND) -E touch_nocreate leveldb_ycsb.grpc.pb.cc

leveldb_ycsb.grpc.pb.h: leveldb_ycsb.pb.cc
	@$(CMAKE_COMMAND) -E touch_nocreate leveldb_ycsb.grpc.pb.h

CMakeFiles/leveldb_server.dir/leveldb_server.cc.o: CMakeFiles/leveldb_server.dir/flags.make
CMakeFiles/leveldb_server.dir/leveldb_server.cc.o: leveldb_server.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/qwe52165/GRPC-levelDB/leveldb_server/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/leveldb_server.dir/leveldb_server.cc.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/leveldb_server.dir/leveldb_server.cc.o -c /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_server.cc

CMakeFiles/leveldb_server.dir/leveldb_server.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/leveldb_server.dir/leveldb_server.cc.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_server.cc > CMakeFiles/leveldb_server.dir/leveldb_server.cc.i

CMakeFiles/leveldb_server.dir/leveldb_server.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/leveldb_server.dir/leveldb_server.cc.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_server.cc -o CMakeFiles/leveldb_server.dir/leveldb_server.cc.s

CMakeFiles/leveldb_server.dir/leveldb_server.cc.o.requires:

.PHONY : CMakeFiles/leveldb_server.dir/leveldb_server.cc.o.requires

CMakeFiles/leveldb_server.dir/leveldb_server.cc.o.provides: CMakeFiles/leveldb_server.dir/leveldb_server.cc.o.requires
	$(MAKE) -f CMakeFiles/leveldb_server.dir/build.make CMakeFiles/leveldb_server.dir/leveldb_server.cc.o.provides.build
.PHONY : CMakeFiles/leveldb_server.dir/leveldb_server.cc.o.provides

CMakeFiles/leveldb_server.dir/leveldb_server.cc.o.provides.build: CMakeFiles/leveldb_server.dir/leveldb_server.cc.o


CMakeFiles/leveldb_server.dir/histogram.cc.o: CMakeFiles/leveldb_server.dir/flags.make
CMakeFiles/leveldb_server.dir/histogram.cc.o: histogram.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/qwe52165/GRPC-levelDB/leveldb_server/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/leveldb_server.dir/histogram.cc.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/leveldb_server.dir/histogram.cc.o -c /home/qwe52165/GRPC-levelDB/leveldb_server/histogram.cc

CMakeFiles/leveldb_server.dir/histogram.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/leveldb_server.dir/histogram.cc.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/qwe52165/GRPC-levelDB/leveldb_server/histogram.cc > CMakeFiles/leveldb_server.dir/histogram.cc.i

CMakeFiles/leveldb_server.dir/histogram.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/leveldb_server.dir/histogram.cc.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/qwe52165/GRPC-levelDB/leveldb_server/histogram.cc -o CMakeFiles/leveldb_server.dir/histogram.cc.s

CMakeFiles/leveldb_server.dir/histogram.cc.o.requires:

.PHONY : CMakeFiles/leveldb_server.dir/histogram.cc.o.requires

CMakeFiles/leveldb_server.dir/histogram.cc.o.provides: CMakeFiles/leveldb_server.dir/histogram.cc.o.requires
	$(MAKE) -f CMakeFiles/leveldb_server.dir/build.make CMakeFiles/leveldb_server.dir/histogram.cc.o.provides.build
.PHONY : CMakeFiles/leveldb_server.dir/histogram.cc.o.provides

CMakeFiles/leveldb_server.dir/histogram.cc.o.provides.build: CMakeFiles/leveldb_server.dir/histogram.cc.o


CMakeFiles/leveldb_server.dir/perf_log2.cc.o: CMakeFiles/leveldb_server.dir/flags.make
CMakeFiles/leveldb_server.dir/perf_log2.cc.o: perf_log2.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/qwe52165/GRPC-levelDB/leveldb_server/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/leveldb_server.dir/perf_log2.cc.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/leveldb_server.dir/perf_log2.cc.o -c /home/qwe52165/GRPC-levelDB/leveldb_server/perf_log2.cc

CMakeFiles/leveldb_server.dir/perf_log2.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/leveldb_server.dir/perf_log2.cc.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/qwe52165/GRPC-levelDB/leveldb_server/perf_log2.cc > CMakeFiles/leveldb_server.dir/perf_log2.cc.i

CMakeFiles/leveldb_server.dir/perf_log2.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/leveldb_server.dir/perf_log2.cc.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/qwe52165/GRPC-levelDB/leveldb_server/perf_log2.cc -o CMakeFiles/leveldb_server.dir/perf_log2.cc.s

CMakeFiles/leveldb_server.dir/perf_log2.cc.o.requires:

.PHONY : CMakeFiles/leveldb_server.dir/perf_log2.cc.o.requires

CMakeFiles/leveldb_server.dir/perf_log2.cc.o.provides: CMakeFiles/leveldb_server.dir/perf_log2.cc.o.requires
	$(MAKE) -f CMakeFiles/leveldb_server.dir/build.make CMakeFiles/leveldb_server.dir/perf_log2.cc.o.provides.build
.PHONY : CMakeFiles/leveldb_server.dir/perf_log2.cc.o.provides

CMakeFiles/leveldb_server.dir/perf_log2.cc.o.provides.build: CMakeFiles/leveldb_server.dir/perf_log2.cc.o


CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o: CMakeFiles/leveldb_server.dir/flags.make
CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o: leveldb_ycsb.pb.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/qwe52165/GRPC-levelDB/leveldb_server/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o -c /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_ycsb.pb.cc

CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_ycsb.pb.cc > CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.i

CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_ycsb.pb.cc -o CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.s

CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o.requires:

.PHONY : CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o.requires

CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o.provides: CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o.requires
	$(MAKE) -f CMakeFiles/leveldb_server.dir/build.make CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o.provides.build
.PHONY : CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o.provides

CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o.provides.build: CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o


CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o: CMakeFiles/leveldb_server.dir/flags.make
CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o: leveldb_ycsb.grpc.pb.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/qwe52165/GRPC-levelDB/leveldb_server/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building CXX object CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o -c /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_ycsb.grpc.pb.cc

CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_ycsb.grpc.pb.cc > CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.i

CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/qwe52165/GRPC-levelDB/leveldb_server/leveldb_ycsb.grpc.pb.cc -o CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.s

CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o.requires:

.PHONY : CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o.requires

CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o.provides: CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o.requires
	$(MAKE) -f CMakeFiles/leveldb_server.dir/build.make CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o.provides.build
.PHONY : CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o.provides

CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o.provides.build: CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o


# Object files for target leveldb_server
leveldb_server_OBJECTS = \
"CMakeFiles/leveldb_server.dir/leveldb_server.cc.o" \
"CMakeFiles/leveldb_server.dir/histogram.cc.o" \
"CMakeFiles/leveldb_server.dir/perf_log2.cc.o" \
"CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o" \
"CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o"

# External object files for target leveldb_server
leveldb_server_EXTERNAL_OBJECTS =

leveldb_server: CMakeFiles/leveldb_server.dir/leveldb_server.cc.o
leveldb_server: CMakeFiles/leveldb_server.dir/histogram.cc.o
leveldb_server: CMakeFiles/leveldb_server.dir/perf_log2.cc.o
leveldb_server: CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o
leveldb_server: CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o
leveldb_server: CMakeFiles/leveldb_server.dir/build.make
leveldb_server: grpc/libgrpc++_unsecure.a
leveldb_server: grpc/third_party/protobuf/libprotobuf.a
leveldb_server: PMIndexDB/libleveldb.a
leveldb_server: grpc/libgrpc_unsecure.a
leveldb_server: grpc/libgpr.a
leveldb_server: grpc/third_party/zlib/libz.a
leveldb_server: grpc/third_party/cares/cares/lib/libcares.a
leveldb_server: grpc/libaddress_sorting.a
leveldb_server: /usr/lib/x86_64-linux-gnu/libpthread.so
leveldb_server: /usr/local/lib/libpmem.so
leveldb_server: /usr/local/lib/libpmemcto.so
leveldb_server: /usr/local/lib/libpmemobj.so
leveldb_server: /usr/local/lib/libpmemlog.so
leveldb_server: CMakeFiles/leveldb_server.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/qwe52165/GRPC-levelDB/leveldb_server/CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Linking CXX executable leveldb_server"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/leveldb_server.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/leveldb_server.dir/build: leveldb_server

.PHONY : CMakeFiles/leveldb_server.dir/build

CMakeFiles/leveldb_server.dir/requires: CMakeFiles/leveldb_server.dir/leveldb_server.cc.o.requires
CMakeFiles/leveldb_server.dir/requires: CMakeFiles/leveldb_server.dir/histogram.cc.o.requires
CMakeFiles/leveldb_server.dir/requires: CMakeFiles/leveldb_server.dir/perf_log2.cc.o.requires
CMakeFiles/leveldb_server.dir/requires: CMakeFiles/leveldb_server.dir/leveldb_ycsb.pb.cc.o.requires
CMakeFiles/leveldb_server.dir/requires: CMakeFiles/leveldb_server.dir/leveldb_ycsb.grpc.pb.cc.o.requires

.PHONY : CMakeFiles/leveldb_server.dir/requires

CMakeFiles/leveldb_server.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/leveldb_server.dir/cmake_clean.cmake
.PHONY : CMakeFiles/leveldb_server.dir/clean

CMakeFiles/leveldb_server.dir/depend: leveldb_ycsb.pb.cc
CMakeFiles/leveldb_server.dir/depend: leveldb_ycsb.pb.h
CMakeFiles/leveldb_server.dir/depend: leveldb_ycsb.grpc.pb.cc
CMakeFiles/leveldb_server.dir/depend: leveldb_ycsb.grpc.pb.h
	cd /home/qwe52165/GRPC-levelDB/leveldb_server && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/qwe52165/GRPC-levelDB/leveldb_server /home/qwe52165/GRPC-levelDB/leveldb_server /home/qwe52165/GRPC-levelDB/leveldb_server /home/qwe52165/GRPC-levelDB/leveldb_server /home/qwe52165/GRPC-levelDB/leveldb_server/CMakeFiles/leveldb_server.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/leveldb_server.dir/depend

