/* 
 * File:   ReadSignals.c
 * Author: Tom Pepels
 *
 * Created on 23 september 2011
 */

#include <stdio.h>
#include "edflib.h"

struct edf_hdr_struct hdr;
struct edf_hdr_struct *hdr_p;

int openEDFFile(char *path) {
    hdr_p = &hdr;
    return edfopen_file_readonly(path, &hdr, EDFLIB_DO_NOT_READ_ANNOTATIONS);
}

int closeEDFFile() {
    int status = edfclose_file(hdr_p->handle);
    hdr_p = NULL;
    return status;
}

int noOfSignals() {
    if (hdr_p != NULL)
        return hdr_p->edfsignals;
    else
        return -1;
}

char* signalName(int signal) {
    if (hdr_p != NULL) {
        return hdr_p->signalparam[signal].label;
    } else {
        return "";
    }
}

char* physicalDimension(int signal) {
        if (hdr_p != NULL) {
        return hdr_p->signalparam[signal].physdimension;
    } else {
        return "";
    }
}

int doubleSize() {
    return sizeof(double);
}

int readSamples(int signal, int samples, double *buf) {
    if (hdr_p != NULL) {
        int r = edfread_physical_samples(hdr_p->handle, signal, samples, buf);
        /*int i = 0;
        for (i = 0; i < r; i++) {
            printf("Sample %d: %f \n", i, buf[i]);
        } */
        return r;
    } else {
        return -1;
    }
}