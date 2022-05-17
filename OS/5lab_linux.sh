#!/bin/bash
function swap() {
mv $1 $1._tmp && mv $2 $1 && mv $1._tmp $2
}
mydir1=$1
    echo "$mydir1"
if [ -e $mydir1 ]
then
	echo "File1 was found"
else
	echo "File1 wasn't found"
	exit 1
fi
mydir2=$2
if [ -e $mydir2 ]
then
	echo "File2 was found"
else
	echo "File2 wasn't found"
	exit 1
fi
(swap $1 $2)
echo "Done"
