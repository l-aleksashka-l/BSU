
#include <iostream>
#include <windows.h>
#include <string>
#include <fstream>
#include <ctime>
#include <map>
#include <regex>

using namespace std;
typedef std::pair<std::string, int> pairs;
ifstream fin("input.txt");
ofstream fout("output.txt");
map<std::string, int> word_counter;

struct files {
	string fin = "input.txt";
	string fout = "output.txt";
};


DWORD WINAPI calculation(LPVOID arg) {
	string text;
	while (fin >> text) {


		word_counter[text]++;
	
	}
	std::vector<pairs> vec;


	std::copy(word_counter.begin(),
		word_counter.end(),
		std::back_inserter<std::vector<pairs>>(vec));


	std::sort(vec.begin(), vec.end(),
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

	return 0;
}

int main() {
	files fil;
	HANDLE threadHandler;
	DWORD threadId;
	threadHandler = CreateThread(NULL, 0, calculation, static_cast<void*>(&fil), 0, &threadId);
	if (threadHandler == NULL)
		return GetLastError();
	clock_t start = clock();
	WaitForSingleObject(threadHandler, INFINITE);
	clock_t end = clock();
	CloseHandle(threadHandler);
	clock_t time = end - start;
	cout << time << "ms" << "\n";

	return 0;
}


