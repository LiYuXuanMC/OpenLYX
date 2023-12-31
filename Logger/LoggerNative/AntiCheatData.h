#pragma once
#include <string>
#include <vector>

struct OffsetData {
	int offset;
	std::vector<BYTE> bytes;
};

static std::vector<std::string> NACDllMD5 = { "85b45f9bcc14004ac09f91bcc0b4d3ac"};
static std::vector<OffsetData> GetStaticMethodIDOffset = {
	{0xAE0,{0x48,0x89,0x5C,0x24,0x08,0x48,0x89,0x6C}},
};
static std::vector<OffsetData> GetMethodIDOffset = {
    {0x940,{0x48,0x89,0x5C,0x24,0x08,0x48,0x89,0x6C}},
};
static std::vector<OffsetData> RegisterNativesOffset = {
    {0x9030,{0x48,0x89,0x5C,0x24,0x18,0x55,0x56,0x57}},
    {0x8F20,{0x48,0x89,0x5C,0x24,0x18,0x55,0x56,0x57}},
};
static std::vector<OffsetData> GetClassSignatureOffset = {
    {0x33EF80,{0x48,0x89,0x6C,0x24,0x10,0x48,0x89,0x74,0x24,0x18}},
    {0x3409E0,{0x48,0x89,0x6C,0x24,0x10,0x48,0x89,0x74,0x24,0x18}},
};
static std::vector<OffsetData> RetransformClassesOffset = {
    {0x343190,{0x48,0x89,0x6C,0x24,0x10,0x48,0x89,0x74,0x24,0x18}},
    {0x344BF0,{0x48,0x89,0x6C,0x24,0x10,0x48,0x89,0x74,0x24,0x18}},
};
static std::vector<OffsetData> AddCapabilitiesOffset = {
    {0x34BF30,{0x48,0x89,0x74,0x24,0x10,0x57,0x48,0x83,0xEC,0x40}},
    {0x34D990,{0x48,0x89,0x74,0x24,0x10,0x57,0x48,0x83,0xEC,0x40}},
};

BYTE* toBytes(std::vector<BYTE> bytes) {
    BYTE* a = new BYTE[bytes.size()];
    for (int i = 0; i < bytes.size(); i++)
    {
        a[i] = bytes[i];
    }
    return a;
}