package moe.quill.stratumcommonutils.Utils;

public class PackageUtils {
    public static String getReflectivePackageName(Class<?> queryClass) {
        final var packageName = queryClass.getPackageName();
        return packageName.substring(0, packageName.lastIndexOf('.'));
    }
}
