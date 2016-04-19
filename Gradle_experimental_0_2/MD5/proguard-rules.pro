-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-dontoptimize
-ignorewarning
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepclasseswithmembernames class * {
    native <methods>;
}

-keep class com.bihe0832.md5.MD5 {public *;}


-dontwarn java.nio.file.Files
-dontwarn java.nio.file.Path
-dontwarn java.nio.file.OpenOption
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement