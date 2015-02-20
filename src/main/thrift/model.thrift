namespace java com.github.lukaszbudnik.thrift.model

struct TestCaseV1 {
    1: string name;
    2: optional binary content;
}

struct TestRunV1 {
    1: string name;
    2: i32 concurrencyLevel;
    3: list<TestCaseV1> testCases;
}

struct TestRunV2 {
    1: string name;
    3: list<TestCaseV1> testCases;
    4: optional string timezone;
}

