#include <cstdio>
#include <sys/time.h>
#include <cstdint>
#include <cstdarg>
#include "perf_log2.h"

static PerfLog2* log;

void CreatePerfLog2() {
  log = new PerfLog2;
}

uint64_t NowMicros2() {
  struct timeval tv;
  gettimeofday(&tv, NULL);
  return static_cast<uint64_t>(tv.tv_sec) * 1000000 + tv.tv_usec;
}

void LogMicros2(Type2 type, uint64_t micros) {
  log->LogMicro2(type, micros);
}

std::string GetInfo2() {
  return log->GetInfo2();
}

std::string GetHistogram2() {
  return log->GetHistogram2();
}

void ClosePerfLog2() {
  delete log;
}

void ClearLogs2() {
  log->ClearLog2();
}
