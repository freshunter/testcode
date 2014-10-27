#!/bin/sh
##########################################################################################
####lib init
##########################################################################################

LIB_PATH=/lib

###distinguish from jvm  32/64
JAVA_VER="`$JAVA_HOME/bin/java -version 2>&1`"
#echo javaver:$JAVA_VER
case "$JAVA_VER" in
    *64-Bit*)
	JVM_BIT=64
    echo "64-Bit jvm"
    ;;
    *)
	JVM_BIT=32
    echo "32-Bit jvm"
    ;;
  esac
echo JVM:$JVM_BIT


###link jni lib 
ln_so()
{
	 if [ -h $2 ]; then 
		echo "delete "$2
		rm -f $2
	fi
    echo Add symbolic link $2 to $1;
    ln -s $1 $2
}

#check the os
OSNAME=`uname`
if [ "$OSNAME" = "SunOS" ]; then
    BIT_TMP=`isainfo -b`
    echo SunOS:"$BIT_TMP"
    if [ "$BIT_TMP" = "64" ]; then
        ln_so ${LIB_PATH}/libFlxCore64.so.2014.03.sparc64 ${LIB_PATH}/libFlxCore64.so
    else
		ln_so ${LIB_PATH}/libFlxCore.so.2014.03.sparc32 ${LIB_PATH}/libFlxCore.so
    fi
	
elif [ "$OSNAME" = "Linux" ]; then
    BIT_TMP=`getconf LONG_BIT`
    echo Linux:"$BIT_TMP"
#use jvm bit
    if [ "$JVM_BIT" = "64" ]; then
        ln_so ${LIB_PATH}/libFlxCore64.so.2014.03.linux64 ${LIB_PATH}/libFlxCore64.so
    else
		ln_so ${LIB_PATH}/libFlxCore.so.2014.03.linux32 ${LIB_PATH}/libFlxCore.so
    fi
fi
###link jni lib as flexnet required

###add lib path to sys env
LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$LIB_PATH

export LD_LIBRARY_PATH

echo LD_LIBRARY_PATH is $LD_LIBRARY_PATH
###add lib path to sys env






