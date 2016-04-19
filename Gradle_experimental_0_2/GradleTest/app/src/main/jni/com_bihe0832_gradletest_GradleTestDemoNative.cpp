#include <jni.h>
#include <string>
#include "com_bihe0832_gradletest_GradleTestDemoNative.h"

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
    return JNI_VERSION_1_4;
}

/*
 * Class:     com_bihe0832_gradletest_GradleTestDemoNative
 * Method:    getUpperMD5
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_bihe0832_gradletest_GradleTestDemoNative_getUpperMD5
        (JNIEnv *env, jclass, jstring jEncryptString) {

    jclass cls = env->FindClass("com/bihe0832/gradletestlibrary/api/GradelTestApi");
    jclass jGradelTestApiClass = (jclass) env->NewGlobalRef(cls);

    jmethodID method= env->GetStaticMethodID(jGradelTestApiClass, "getUpperMD5", "(Ljava/lang/String;)Ljava/lang/String;");
    jstring jResult = (jstring) env->CallStaticObjectMethod(jGradelTestApiClass, method, jEncryptString);

    env->DeleteLocalRef(cls);
    return jResult;
}

/*
 * Class:     com_bihe0832_gradletest_GradleTestDemoNative
 * Method:    getLowerMD5
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_bihe0832_gradletest_GradleTestDemoNative_getLowerMD5
        (JNIEnv *env, jclass, jstring jEncryptString) {
    jclass cls = env->FindClass("com/bihe0832/gradletestlibrary/api/GradelTestApi");
    jclass jGradelTestApiClass = (jclass) env->NewGlobalRef(cls);

    jmethodID method= env->GetStaticMethodID(jGradelTestApiClass, "getLowerMD5", "(Ljava/lang/String;)Ljava/lang/String;");
    jstring jResult = (jstring) env->CallStaticObjectMethod(jGradelTestApiClass, method, jEncryptString);

    env->DeleteLocalRef(cls);
    return jResult;
}