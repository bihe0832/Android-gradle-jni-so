#!/bin/sh
#函数定义，检测执行结果
function checkResult() {  
   result=$?
   echo "result : $result"
   if [ $result -eq 0 ];then
      echo "checkResult: execCommand succ"
   else
    echo "checkResult: execCommand failed"
    exit $result
   fi
}  

export ANDROID_HOME=$ANDROID_SDK
export JAVA_HOME=`/usr/libexec/java_home -v 1.7`
export ANDROID_NDK_HOME=$ANDROIDNDK_LINUX_R10C
export PATH=$JAVA_HOME/bin:$GRADLE_HOME/bin:$PATH
echo $ANDROID_HOME
echo $JAVA_HOME
echo $ANDROID_NDK_HOME

echo "********build mkdir bin *******"

localPath=`pwd`
echo $localPath
#创建打包目录
if [ ! -d "./bin" ]; then
  mkdir $localPath/bin
fi

#进入打包目录并清空目录
cd $localPath/bin && rm -rf  * && cd $localPath

#构建md5
echo "********build md5 *******"
chmod +x $localPath/MD5/gradlew
cd $localPath/MD5 && ./gradlew clean
cd $localPath/MD5 && ./gradlew build
checkResult

#拷贝最新资源
echo "********copy md5 so and jar *******"
mkdir $localPath/bin/MD5
cp -r $localPath/MD5/app/build/intermediates/ndk/all/release/lib/* $localPath/GradleTest/gradletestlibrary/src/main/jniLibs/
checkResult
cp -r $localPath/MD5/app/build/intermediates/ndk/all/release/lib/* $localPath/bin/MD5/
checkResult
cp -r $localPath/MD5/app/build/intermediates/bundles/all/release/classes.jar $localPath/GradleTest/gradletestlibrary/libs/bihe0832MD5.jar
checkResult
cp -r $localPath/MD5/app/build/intermediates/bundles/all/release/classes.jar $localPath/bin/MD5/bihe0832MD5.jar
checkResult

echo "********build GradleTest*******"
chmod +x $localPath/GradleTest/gradlew
cd $localPath/GradleTest && ./gradlew clean
cd $localPath/GradleTest && ./gradlew build
checkResult

echo "********copy apk *******"
mkdir $localPath/bin/GradleTest
cp $localPath/GradleTest/app/build/outputs/apk/app-all-debug.apk $localPath/bin/GradleTest-debug.apk

echo "********copy libs res *******"
cp -r $localPath/GradleTest/gradletestlibrary/build/outputs/aar/gradletestlibrary-release.aar $localPath/bin/GradleTest/gradletestlibrary-release.aar
checkResult
cp -r $localPath/GradleTest/gradletestlibrary/build/intermediates/bundles/release/classes.jar $localPath/bin/GradleTest/gradletestlibrary.jar
checkResult


