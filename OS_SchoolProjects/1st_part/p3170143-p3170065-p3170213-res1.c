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
int Cseats,runtime;
int counter = Nseats;
int PosCard;
int Account = 0;
int thlefwnhtes = 0;
int CustSeats[Nseats];
pthread_mutex_t M;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
struct timespec timeStart,timeEnd1,timeEnd2;

void *procedureMain(void *threadId) {
	
	clock_gettime(CLOCK_REALTIME,&timeStart);

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

		PosCard = rand() % 100 + 1;

		if (Pcardsucces > PosCard) {

			counter += Cseats;
			printf("Error!Card declined.\n");

		}
		else {
			printf("The customer %d has been served successfully!The number of your reservation is %d and the cost of your reservation is %d.\n", id , id , Cseat * Cseats);	

			for(int j=0;j<Cseats;j++){
				CustSeats[c++]=id;
			}			

			Account += Cseat * Cseats;

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

	thlefwnhtes--;
	rc = pthread_cond_signal(&cond);
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

	double seconds = timeEnd1.tv_sec - timeStart.tv_sec;
	double ns = timeEnd1.tv_nsec - timeStart.tv_nsec;

	printf("Average time of waiting : %e\n" , (seconds + ns)/Ncust);

	double secondsf = timeEnd2.tv_sec - timeStart.tv_sec;
	double nsf = timeEnd2.tv_nsec - timeStart.tv_nsec;

	printf("Average time of the whole reservation : %e\n" , (secondsf + nsf)/Ncust);

	return 0;
}



