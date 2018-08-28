#include <cstdio>
#include <cstdint>
#include <sys/time.h>
#include "histogram.h"

enum Type2 {
  READ = 0,
  UPDATE = 1,
  INSERT = 2,
  SCAN = 3,
  DELETE = 4,
};

class PerfLog2 {
public:
  PerfLog2() {
    read_.Clear();
    update_.Clear();
    insert_.Clear();
    scan_.Clear();
    delete_.Clear();
  }

  ~PerfLog2() = default;

  void LogMicro2(Type2 type, uint64_t micros) {
    switch (type) {
      case READ:
        read_.Add(micros);
        break;
      case UPDATE:
        update_.Add(micros);
        break;
      case INSERT:
        insert_.Add(micros);
        break;
      case SCAN:
        scan_.Add(micros);
        break;
      case DELETE:
        delete_.Add(micros);
        break;
    }
  }

  std::string GetInfo2() {
    std::string r;
    r.append("Read info,\n");
    r.append(read_.GetInfo());
    r.append("Update info,\n");
    r.append(update_.GetInfo());
    r.append("Insert info,\n");
    r.append(insert_.GetInfo());
    r.append("Scan info,\n");
    r.append(scan_.GetInfo());
    r.append("Delete info,\n");
    r.append(delete_.GetInfo());
    return r;
  }

  std::string GetHistogram2() {
    std::string r;
    r.append("Read info,\n");
    r.append(read_.GetHistogram());
    r.append("Update info,\n");
    r.append(update_.GetHistogram());
    r.append("Insert info,\n");
    r.append(insert_.GetHistogram());
    r.append("Scan info,\n");
    r.append(scan_.GetHistogram());
    r.append("Delete info,\n");
    r.append(delete_.GetHistogram());
    return r;
  }

  void ClearLog2() {
	  read_.Clear();
	  update_.Clear();
	  insert_.Clear();
	  scan_.Clear();
	  delete_.Clear();
  }

private:
  Histogram read_;
  Histogram update_;
  Histogram insert_;
  Histogram scan_;
  Histogram delete_;

};

extern void CreatePerfLog2();
extern uint64_t NowMicros2();
extern void LogMicros2(Type2, uint64_t);
extern std::string GetInfo2();
extern std::string GetHistogram2();
extern void ClosePerfLog2();
extern void ClearLogs2();
