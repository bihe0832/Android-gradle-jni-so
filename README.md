# Android-gradle-jni-so

## 简介

An Android Project with more than one module，the app module(some api developed by jni) depend on an Android Library which has native so.

该项目是一个使用Android Studio创建的，通过gradle编译的，存在多个模块的工程的gradle构建的事例。其中存在多个模块的工程中包含:

- 一个Android Library的模块，该模块使用了第三方的jar和so
- 一个Android Application模块，该模块引用上面的Android Library，自身包含jni的接口调用。
## 体验方式

#### Demo 下载：
	
- [点击下载](http://blog.bihe0832.com/public/resource/GradleTest-debug.apk)
	
- 扫码下载APK：
	
![获取大写md5值的函数调用流程](http://blog.bihe0832.com/public/images/gradle-test-apk-download.png)

#### 运行工程：

- 准备工作

	下面的两种方式都可以运行，不过运行之前要先根据自己的网络环境修改下面几个文件。
	- 修改GradleTest下local.properies中的ndk.dir和sdk.dir的环境配置	- 修改GradleTest下gradle/wrapper/gradle-wrapper.properties 关于使用的gradle-2.5-all.zip的地址的修改
	- 修改GradleTest下build.gradle中对于使用的maven库的声明
	- 修改MD5下local.properies中的ndk.dir和sdk.dir的环境配置
	- 修改MD5下gradle/wrapper/gradle-wrapper.properties 关于使用的gradle-2.5-all.zip的地址的修改
	- 修改MD5下build.gradle中对于使用的maven库的声明

- 运行方式	
	
	- 使用Android Studio 逐个导入
	- 直接在根目录运行build.sh
	 
## 工程介绍

在项目中存在两个Android Studio的工程

### GradleTest

GradleTest是核心工程，它里面包含了一个Android Library的模块gradletestlibrary和一个Android Application的模块app。

- **gradletestlibrary**

	一个Android Library的模块，他引入了第三方的so和jar(MD5工程的编译产出)，并对第三方的jar和so做了封装和调用。

- **app**

	一个Android Application的模块，他引用gradletestlibrary，同时里面包含jni的代码，通过native的方式调用gradletestlibrary提供的方法。

### MD5

一个普通的Android 工程，他包含java和jni代码，最终打包后对外提供jar包和so。模拟第三方的jar和so提供给GradleTest使用。

## 关键代码结构

	Android-gradle-jni-so
		│
		├─── build.sh 生成最终产出物的简单构建脚本，直接运行会生成最终的资源、apk和核心文件
		│
		├─── GradleTest 核心工程，一个存在多个Module的Android工程
		│		│
		│		├── app 基于Android Application的模块，引用gradletestlibrary，通过native的方式调用接口。
		│		│
		│		└── gradletestlibrary 基于Android Library的模块，引入MD5提供的jar和so
		│		
		├─── MD5 普通Android工程，他包含java和jni代码，最终打包后对外提供jar包和so
		│
	   	└─── README.md 项目工程介绍
	   	
## 接口调用

下面用图示简单梳理demo中获取大写md5值的函数调用流程

![获取大写md5值的函数调用流程](http://blog.bihe0832.com/public/images/gradle-test-func-call.png)

## 使用场景

说实话，这么设计一个工程是有点复杂，但是却存在实实际际的使用场景。上面的工程是自己根据一直以来SDK开发时实际经验总结，下面对该事例中的两个工程以及对应模块做个现实中的身份映射。

### 一点说明

首先、这个完整的架构是主要是提供给SDK的开发者或者大型项目存在公共代码库的情况下使用的。如果项目代码很少，确实不用搭建如此复杂的框架。

### 具体身份映射

- MD5  ————  映射SDK开发者或者项目公共库依赖的第三方库，例如SDK的开发者会用到一些第三方的数据统计、账号登陆SDK等
- GradleTest ————  SDK开发者或者项目的工程
	- gradletestlibrary SDK的具体实现，或者公共库的具体实现，最终可能也会以jar、甚至so的方式对外提供
	- app 具体的应用工程，可能是SDK的开发者对外提供的配套demo，也可能是具体的开发者的应用工程，这就是一个普通的Android工程。

## 特别说明

1. 本文档只是重点介绍整个项目的工程架构，对于具体的语法没有做说明，后续会逐步整理增加到博客。
- 目前gradle支持native构建需要使用特定的gradle版本和gradle tools版本以及NDK版本。具体相关说明可以参照[Experimental Plugin User Guide](http://blog.bihe0832.com/Experimental_Plugin_User_Guide.html)中的说明。对于该项目来说，不要修改项目build.gradle中buildscript内对于gradle tools的版本的声明以及根目录gradle下gradle-wrapper.properties中对于gradle版本的说明。也就是说：
	- 必须使用gradle-2.5-all 和 com.android.tools.build:gradle-experimental:0.2.+
	- build.gradle 代码如下：
		
			buildscript {
				
				……
				
			    dependencies {
			        classpath "com.android.tools.build:gradle-experimental:0.2.+"
			    }
			}
	- gradle-wrapper.properties 内容如下：

			#Tue Dec 15 16:08:58 CST 2015
			distributionBase=GRADLE_USER_HOME
			distributionPath=wrapper/dists
			zipStoreBase=GRADLE_USER_HOME
			zipStorePath=wrapper/dists
			distributionUrl=http\://services.gradle.org/distributions/gradle-2.5-all.zip