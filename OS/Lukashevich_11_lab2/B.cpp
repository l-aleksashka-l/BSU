#include <iostream>
#include <windows.h>
#include <string>
#include <fstream>
#include <ctime>
#include <sstream>
#include <map>
#include <set>
#include <stack>
#include <vector>
#include<algorithm>
using namespace std;

ifstream fin("input.txt");
ofstream fout("output.txt");


struct taskDate {
	string str;
};

stack<pair<taskDate, double>> result;
stack<pair<taskDate, string>> noSol;

typedef struct times {
	clock_t start;
	clock_t end;
};

map<int, times> arrtime;
map<int, int> statistics;

int min_time = INT_MAX;
int max_time = INT_MIN;

DWORD WINAPI calculate2(LPVOID arg) {
	if (arrtime.find(GetCurrentThreadId()) == arrtime.end()) {
		pair<clock_t, clock_t> t(clock(), clock());
		times current;
		current.start = clock();
		current.end = clock();
		int i1 = GetCurrentThreadId();
		statistics.insert(make_pair(i1, 0));
		arrtime.insert(make_pair(i1, current));
	}
	else
		statistics[GetCurrentThreadId()] = ++statistics[GetCurrentThreadId()];

	HANDLE mutex = CreateMutex(NULL, FALSE, L"functThread");

	if (mutex == NULL)
		throw "(functThread) mutex failed\n";

	WaitForSingleObject(mutex, INFINITE);

	stack<taskDate>& stac = *(stack<taskDate>*)arg;

	if (stac.empty()) {
		ReleaseMutex(mutex);
		return 0;
	}

	clock_t st = clock();
	taskDate cur = stac.top();
	stac.pop();

	bool flag = true;
	string text=cur.str;
	map<std::string, int> word_counter;
	typedef std::pair<std::string, int> pairs;
	
	std::istringstream ist(text);
	std::string tmp;
	while (ist >> tmp) {
		word_counter[tmp]++;
	}
		std::vector<pairs> vec;
	
	
		std::copy(word_counter.begin(),
			word_counter.end(),
			std::back_inserter<std::vector<pairs>>(vec));
	
	
		sort(vec.begin(), vec.end(),
			[](const pairs& l, const pairs& r)
			{
				
	
				return l.second > r.second;
			});
		fout << vec[0].first << endl;
		for (int i = 1; i < vec.size();i++) {
			if (vec[i].second==vec[0].second) {
				fout << vec[i].first<<endl;
			}
		}

	ReleaseMutex(mutex);
	arrtime[GetCurrentThreadId()].end = clock();
	clock_t en = clock();

	if (en - st > max_time)
		max_time = en - st;
	else if (en - st < min_time)
		min_time = en - st;

	calculate2(arg);
}
string generate() {
	string str = "abcdefghijklmnopqrstuvwxyz";
	
	string ret= "";
	int lengthText = 10;
	for (int i = 0; i < lengthText;i++) {
		
		int len = 1+ rand() % 3;
		for (int j = 0; j < len;j++) {
			ret += str[(rand()%25)];
		}
		ret += " ";
	}
	return ret;
}
int main() {
	setlocale(LC_ALL, "rus");
	srand(time(NULL));
	int  TaskCount;
	int  ThreadCount;
	stack<taskDate> stak;
	cout << "Task count:" << endl;
	cin >> TaskCount;
	cout << "Thread count:" << endl;
	cin >> ThreadCount;
	cout << "\n";
	HANDLE* handler = new HANDLE[ThreadCount];
	DWORD* id = new DWORD[ThreadCount];
	srand(time(NULL));
	for (int i = 0; i < TaskCount; i++) {
		taskDate tr;
		tr.str = generate();
		stak.push(tr);
	}
	for (int i = 0; i < ThreadCount; i++) {
		handler[i] = CreateThread(NULL, 0, calculate2, (void*)(&stak), 0, &id[i]);
		if (handler[i] == NULL)
			return GetLastError();
	}
	WaitForMultipleObjects(ThreadCount, handler, TRUE, INFINITE);
	for (int i = 0; i < ThreadCount; i++) {
		CloseHandle(handler[i]);
	}
	cout << "Tasks solved: " << result.size() << "\n";
	for (map <int, int> ::iterator it = statistics.begin(); it != statistics.end(); it++) {
		cout << "Thread number: " << it->first << " Tasks solved:  " << it->second << "\n";
	}
	cout << "\n";
	for (map <int, times> ::iterator it = arrtime.begin(); it != arrtime.end(); it++) {
		cout << "Thread number: " << it->first << " Time: " << it->second.end - it->second.start << "ms\n";
	}
	cout << "\n";
	cout << "MAX TIMES SOLVING: " << max_time << "ms\n";
	cout << "MIN TIMES SOLVING: " << min_time << "ms\n\n";
	int size2 = result.size();
	int size3 = noSol.size();
	clock_t start_time = clock();
	for (int i = 0; i < size2; i++) {
		pair<taskDate, double> dot = result.top();
	//	fout << dot.first.str <<"\n";
		result.pop();
	}
	for (int i = 0; i < size3; i++) {
		pair<taskDate, string>dot = noSol.top();
	//	fout << dot.first.str << "\n";
		noSol.pop();
	}
	clock_t end_time = clock();
	cout << "Time to write in file: " << end_time - start_time << "ms\n";
	return 0;
}
