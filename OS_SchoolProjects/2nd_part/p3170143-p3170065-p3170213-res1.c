#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/time.h>
#include "1stAssignmentlit.h"

int Ncust;
unsigned int seed;
int tc;
int c=1;
int cost = 0;
int Cseats,runtime, runtime2;
int counter = Nseats;
int PosCard;
int Account = 0;
int thlefwnhtes = 0;
int cashier = 0;
int CustSeats[Nseats];
boolean Seats[10][25] = false;
pthread_mutex_t M,M2,Ì3;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond2 = PTHREAD_COND_INITIALIZER;
struct timespec timeStart1, timeStart2,timeEnd1,timeEnd2;

void *procedureMain(void *threadId) {
	
	clock_gettime(CLOCK_REALTIME,&timeStart1);

	int rc;
	int id = (int*)threadId;

	rc = pthread_mutex_lock(&M);

	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		pthread_exit(&rc);
	}

	while (thlefwnhtes == 8) {

		printf("The customer %d is blocked.No available employee.\n" , id);
		rc = pthread_cond_wait(&cond,&M);

	}

	clock_gettime(CLOCK_REALTIME,&timeEnd1);
	
	printf("The customer %d is connected with a host.\n" , id);
	thlefwnhtes++;

	Cseats = rand() % Nseathigh + Nseatlow;

	runtime = rand() % Tseathigh + Tseatlow;
	
	rc = pthread_mutex_unlock(&M);
	sleep(runtime);
	rc = pthread_mutex_lock(&M);

	if (Cseats > counter) {
		printf("We are sorry!There are no available seats.\n");
	}
	else if (counter == 0) {
		printf("We are sorry!The theater is sold out.\n");
	}
	else {
		counter -= Cseats;
		PosZone = rand() % 100 + 1;
		PosCard = rand() % 100 + 1;

		if (PosZone <= 20/100) {

			rc = pthread_mutex_lock(&M3);

			int c = 0;
			for (int i = 0;i < 5;i++) {
				for (int j = 0; j < 10; j++) {
					if (c == Cseats) {
						break;
					}
					else if (Seats[i][j] == false) {
						//Seats[i][j] = true;
						c++;
					}
					else {
						if (c == 0) {

						}
						else {
							c--;
						}
					};
				}
			}
			if (c < Cseats) {
				printf("We are sorry.There aren't enough seats available.");
			}
			else {
				cost = Cseats * 20;
			}
			rc = pthread_mutex_unlock(&M3);
		}

		if (PosZone > 20 / 100 && PosZone <= 60 / 100) {

			rc = pthread_mutex_lock(&M3);

			int c = 0;
			for (int i = 5; i < 15; i++) {
				for (int j = 0; j < 10; j++) {
					if (c == Cseats) {
						break;
					}
					else if (Seats[i][j] == false) {
						//Seats[i][j] = true;
						c++;
					}
					else {
						if (c == 0) {

						}
						else {
							c--;
						}
					};
				}
			}
			if (c < Cseats) {
				printf("We are sorry.There aren't enough seats available.");
			}
			else {
				cost = Cseats * 25;
			}
			rc = pthread_mutex_unlock(&M3);
		}

		if (PosZone > 60 / 100 && PosZone <= 1) {

			rc = pthread_mutex_lock(&M3);

			int c = 0;
			for (int i = 15; i < 25; i++) {
				for (int j = 0; j < 10; j++) {
					if (c == Cseats) {
						break;
					}
					else if (Seats[i][j] == false) {
						//Seats[i][j] = true;
						c++;
					}
					else {
						if (c == 0) {
							
						}
						else { 
							c--;
						}
					};
				}
			}
			if (c < Cseats) {
				printf("We are sorry.There aren't enough seats available.");
			}
			else {
				cost = Cseats * 30;
			}
			rc = pthread_mutex_unlock(&M3);
		}

		for(int j=0;j<Cseats;j++){
			CustSeats[c++]=id;
		}			

		while (cashier == 4) {

			printf("The customer %d is blocked.No available cashier.\n", id);
			rc = pthread_cond_wait(&cond2, &M2);

		}

		clock_gettime(CLOCK_REALTIME, &timeStart2);

		cashier++;

		runtime2 = rand() % Tcashhigh + Tcashlow;

		if (Pcardsucces > PosCard) {

			counter += Cseats;
			printf("Error!Card declined.\n");

		}else{

			Account += cost;

			switch (Cseats) {
				case 1: printf("Your seat is: %d\n", Nseats - counter);
					break;
				case 2: printf("Your seats are: %d and %d\n", Nseats - counter, Nseats - counter + 1);
					break;
				default: printf("Your seats are: %d to %d\n", Nseats - counter, Nseats - counter + Cseats);
					break;
			}
		}
	}

	cashier--;
	thlefwnhtes--;

	rc = pthread_cond_signal(&cond);
	if (rc != 0) {
		printf("ERROR: return code from pthread_cond_signal() is %d\n", rc);
		pthread_exit(&rc);
	}

	rc = pthread_mutex_unlock(&M2);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		pthread_exit(&rc);
	}

	rc = pthread_mutex_unlock(&M);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		pthread_exit(&rc);
	}

	clock_gettime(CLOCK_REALTIME,&timeEnd2);
	pthread_exit(NULL);
}


int main(int argc, const char* argv[]) {

	if (argc != 3) {
		printf("Error!Invalid number of arguments!\n");
		return -1;
	}

	Ncust = atoi(argv[1]);
	seed = atoi(argv[2]);

	rand_r(&seed);

	pthread_t threads[Ncust];
	int id[Ncust];
	int rc;

	rc = pthread_mutex_init(&M , NULL);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_init() is %d\n", rc);
		exit(-1);
	}
	

	for(int i = 0; i < Ncust; i++){
		id[i] = i + 1;
		rc = pthread_create(&threads[i], NULL, procedureMain, id[i]);
		if (rc != 0) {
			printf("ERROR: return code from pthread_create() is %d\n", rc);
			pthread_exit(&rc);
		}
	}

	for(int i = 0; i < Ncust; i++){
		rc = pthread_join(threads[i], NULL);
		if (rc != 0) {
				printf("ERROR: return code from pthread_join() is %d\n", rc);
				pthread_exit(&rc);
		}
	}

	rc = pthread_mutex_destroy(&M);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);
	}

	rc = pthread_cond_destroy(&cond);
	if (rc != 0) {
		printf("ERROR: return code from pthread_cond_destroy() is %d\n", rc);
		exit(-1);
	}

	for(int j=1;j<=Nseats;j++){
		printf("Seat %d / Customer %d , " , j , CustSeats[j]);
	}	

	printf("\n");
	printf("The total income is : %d\n" , Account);

	double seconds = timeEnd1.tv_sec - timeStart1.tv_sec;
	double ns = timeEnd1.tv_nsec - timeStart1.tv_nsec;

	printf("Average time of waiting for the seats: %e\n" , (seconds + ns)/Ncust);

	double seconds2 = timeEnd2.tv_sec - timeStart2.tv_sec;
	double ns2 = timeEnd2.tv_nsec - timeStart2.tv_nsec;

	printf("Average time of waiting for the seats: %e\n", (seconds2 + ns2) / Ncust);

	double secondsf = timeEnd2.tv_sec - timeStart1.tv_sec;
	double nsf = timeEnd2.tv_nsec - timeStart1.tv_nsec;

	printf("Average time of the whole reservation : %e\n" , (secondsf + nsf)/Ncust);

	return 0;
}



