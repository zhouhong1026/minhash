#!/bin/bash

JAR_FILE=../../../target/weka-naive-bayes.jar
ERRORS_TMP_FILE=errors.tmp
DISTRIB_TMP_FILE=ditrib.tmp
RESULT_TMP_FILE=result.tmp

DATA_FILE=$1
CLASSIFIER_CLASS=$2
RESULT_DIR=`basename $DATA_FILE`
RESULT_FILE=$RESULT_DIR/`basename $DATA_FILE`-$CLASSIFIER_CLASS.predictions

echo "Data file: $DATA_FILE"
echo "Classifier: $CLASSIFIER_CLASS"
echo "Result file: $RESULT_FILE"

# get results
java -cp $JAR_FILE $CLASSIFIER_CLASS -t $DATA_FILE -split-percentage 70 -p 0 > $RESULT_TMP_FILE

#get errors
cat < $RESULT_TMP_FILE | tail -n +6 | tr -s " " \\t | cut -f5 | tr [0-9\.] "1" | tr -s "1" | tr "+" "0" > $ERRORS_TMP_FILE

# get distribution
cat < $RESULT_TMP_FILE | tail -n +6 | tr "+" " " | tr -s " " \\t | cut -f5 > $DISTRIB_TMP_FILE

# sort by predictions
mkdir -p $RESULT_DIR
pr -tm errors.tmp ditrib.tmp  | tr -s " " \\t | sort -n -r -k2 > $RESULT_FILE

# cleanup
rm -f $ERRORS_TMP_FILE $DISTRIB_TMP_FILE $RESULT_TMP_FILE
