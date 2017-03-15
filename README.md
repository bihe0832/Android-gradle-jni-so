# Android-gradle-jni-so

## 简介

An Android Project with more than one module，the app module(some api developed by jni) depend on an Android Library which has native so.

该项目是一个使用Android Studio创建的，通过gradle编译的，存在多个模块的工程的gradle构建的事例。完整的模拟了第三方SDK，自己SDK以及Demo之前的调用关系以及相关的gradle编译脚本。

**该项目重点介绍不同gradle插件版本下的项目的编译脚本怎么编写。提供的多个版本的项目源码完全一致，仅build.gradle有区别。**

## 体验方式

#### Demo 下载：
	
- [点击下载](http://blog.bihe0832.com/public/resource/GradleTest-debug.apk)
	
- 扫码下载APK：
	
![获取大写md5值的函数调用流程](http://blog.bihe0832.com/public/images/gradle-test-apk-download.png)

#### 运行工程：

如何修改配置及运行工程，请参考本人博客：[终端基于gradle的开源项目运行环境配置指引](
http://blog.bihe0832.com/android-as-gradle-config.html)

## 目录介绍

由于Google最新的gradle-experimental插件对部分语法有调整，加上最近咨询用标准版怎么搞得人比较多，因此最近对项目内容作了一次调整。具体如下：

- `Gradle_1_+`：基于Gradle的1.0以上版本实现
- `Gradle_experimental_0_2`：基于Gradle-experimental的0.2版本实现
- `Gradle_experimental_0_6`：基于Gradle-experimental的0.6版本实现

三个个版本的代码内容完全一致，唯一区别就是build.gradle里面的实现。

## 工程介绍

在项目中存在两个Android Studio的工程

#### MD5

一个普通的Android 工程，他包含java和jni代码，最终打包后对外提供jar包和so。模拟第三方的jar和so提供给GradleTest使用。

#### GradleTest

GradleTest是核心工程，它里面包含了一个Android Library的模块gradletestlibrary和一个Android Application的模块app。

- **gradletestlibrary**

	一个Android Library的模块，他引入了第三方的so和jar(MD5工程的编译产出)，并对第三方的jar和so做了封装和调用。

- **app**

	一个Android Application的模块，他引用gradletestlibrary，同时里面包含jni的代码，通过native的方式调用gradletestlibrary提供的方法。


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

#### 一点说明

首先、这个完整的架构是主要是提供给SDK的开发者或者大型项目存在公共代码库的情况下使用的。如果项目代码很少，确实不用搭建如此复杂的框架。

#### 具体身份映射

- MD5  ————  映射SDK开发者或者项目公共库依赖的第三方库，例如SDK的开发者会用到一些第三方的数据统计、账号登陆SDK等
- GradleTest ————  SDK开发者或者项目的工程
	- gradletestlibrary SDK的具体实现，或者公共库的具体实现，最终可能也会以jar、甚至so的方式对外提供
	- app 具体的应用工程，可能是SDK的开发者对外提供的配套demo，也可能是具体的开发者的应用工程，这就是一个普通的Android工程。

## 特别说明

1. 本文档只是重点介绍整个项目的工程架构，对于具体的语法没有做说明，后续会逐步整理增加到博客。
- 目前gradle支持native构建需要使用特定的gradle版本和gradle tools版本以及NDK版本。具体相关说明可以参照[Experimental Plugin User Guide](http://blog.bihe0832.com/Experimental_Plugin_User_Guide.html)中的说明。对于该项目来说，对于每个目录下的完整工程**不要修改项目build.gradle中buildscript内对于gradle tools的版本的声明以及根目录gradle下gradle-wrapper.properties中对于gradle版本的说明**。也就是说：

	- 使用 com.android.tools.build:gradle-experimental:0.2.+ 必须对应 gradle-2.5-all 。使用 com.android.tools.build:gradle-experimental:0.6.+ 必须对应 gradle-2.10-all 。具体以gradle-experimental:0.2和gradle-2.5-all举例如下：

	- build.gradle 代码如下：
		
			buildscript {
				
				……
				
			    dependencies {
			        classpath "com.android.tools.build:gradle-experimental:0.2.+"
			    }
			}
	- gradle-wrapper.properties 内容如下：

			# Tue Dec 15 16:08:58 CST 2015
			distributionBase=GRADLE_USER_HOME
			distributionPath=wrapper/dists
			zipStoreBase=GRADLE_USER_HOME
			zipStorePath=wrapper/dists
			distributionUrl=http\://services.gradle.org/distributions/gradle-2.5-all.zip
			
## 再次补充

当时写这个事例的时候gradle-experimental插件的0.2版本刚出，发现他对一些语法规则做了调整，不兼容Android gradle插件标准版的语法。也是因为这个原因当时写了对应的文档和事例。

前段时间再去看官方关于gradle experimental的介绍的时候惊诧的发现，最新版本的gradle-experimental插件的语法又部分改回了主流版本的语法规则，就悲催的发现这篇文章仅供参考了。因此这几天彻底重新调整了目录，方便后续编译。