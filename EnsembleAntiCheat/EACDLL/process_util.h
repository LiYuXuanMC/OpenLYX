#ifndef PROCESSUTIL_H
#define PROCESSUTIL_H

#include <Windows.h>
#include <Psapi.h>
#include <vector>

PVOID GetProcessModuleBase(HANDLE hProcess, LPCSTR moduleName)
{

    // ��������ģ��,
    HMODULE hModule[100] = { 0 };
    DWORD dwRet = 0;
    BOOL bRet = ::EnumProcessModules(hProcess, (HMODULE*)(hModule), sizeof(hModule), &dwRet);
    if (FALSE == bRet)
    {
        ::CloseHandle(hProcess);
        return NULL;
    }
    char name[50] = { 0 };
    for (int i = 0; i < dwRet; i++)
    {
        GetModuleBaseNameA(hProcess, hModule[i], name, 50);

        if (!_strcmpi(moduleName, name))
        {
            return hModule[i];
        }
    }

    ::CloseHandle(hProcess);
    return NULL;
}

UINT64 GetFunAddrByName(HANDLE hProcess, LPCSTR ModName, LPCSTR FunName)
{
    HANDLE hMod;
    PVOID BaseAddress = NULL;
    IMAGE_DOS_HEADER dosheader;
    IMAGE_OPTIONAL_HEADER64 opthdr; //IMAGE_OPTIONAL_HEADER64
    IMAGE_EXPORT_DIRECTORY exports;
    USHORT index = 0;
    ULONG addr, i;
    char pFuncName[100] = { 0 };
    PULONG pAddressOfFunctions;
    PULONG pAddressOfNames;
    PUSHORT pAddressOfNameOrdinals;

    //��ȡģ���ַ
    BaseAddress = GetProcessModuleBase(hProcess, ModName);
    if (!BaseAddress) return 0;

    //��ȡPEͷ
    hMod = BaseAddress;
    ReadProcessMemory(hProcess, hMod, &dosheader, sizeof(IMAGE_DOS_HEADER), 0);
    ReadProcessMemory(hProcess, (BYTE*)hMod + dosheader.e_lfanew + 24, &opthdr, sizeof(IMAGE_OPTIONAL_HEADER), 0);
    //ReadProcessMemory(hProcess, (BYTE*)hMod + dosheader.e_lfanew + 24, &opthdr, sizeof(IMAGE_OPTIONAL_HEADER64), 0);

    //���ҵ����� 
    ReadProcessMemory(hProcess, ((BYTE*)hMod + opthdr.DataDirectory[IMAGE_DIRECTORY_ENTRY_EXPORT].VirtualAddress), &exports, sizeof(IMAGE_EXPORT_DIRECTORY), 0);

    pAddressOfFunctions = (ULONG*)((BYTE*)hMod + exports.AddressOfFunctions);
    pAddressOfNames = (ULONG*)((BYTE*)hMod + exports.AddressOfNames);
    pAddressOfNameOrdinals = (USHORT*)((BYTE*)hMod + exports.AddressOfNameOrdinals);

    //�ԱȺ����� 
    for (i = 0; i < exports.NumberOfNames; i++)
    {
        ReadProcessMemory(hProcess, pAddressOfNameOrdinals + i, &index, sizeof(USHORT), 0);
        ReadProcessMemory(hProcess, pAddressOfFunctions + index, &addr, sizeof(ULONG), 0);

        ULONG a = 0;
        ReadProcessMemory(hProcess, pAddressOfNames + i, &a, sizeof(ULONG), 0);
        ReadProcessMemory(hProcess, (BYTE*)hMod + a, pFuncName, 100, 0);
        ReadProcessMemory(hProcess, pAddressOfFunctions + index, &addr, sizeof(ULONG), 0);

        if (!_stricmp(pFuncName, FunName))
        {
            UINT64 funAddr = (UINT64)BaseAddress + addr;
            return funAddr;
        }
    }
    return 0;
}



#endif