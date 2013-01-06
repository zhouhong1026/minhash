#!/bin/bash

DATA_DIR="../../../data/"
FILES[0]=iris.arff
FILES[1]=smoking.arff
FILES[2]=breast-cancer.arff

CLASSIFIERS[0]=pl.poznan.put.hied.classifier.bayes.NaiveBayesClassifier
CLASSIFIERS[1]=weka.classifiers.bayes.NaiveBayes
CLASSIFIERS[2]=weka.classifiers.trees.J48

echo "Generating predictions..."
for CLS in "${CLASSIFIERS[@]}"
do
	for DF in "${FILES[@]}"
	do
		DF=$DATA_DIR$DF
		echo ">>> Generating predicion for $CLS: $DF"
		./generate-prediction.sh $DF $CLS
	done
done
