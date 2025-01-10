package com.squareup.picasso;

import java.io.PrintWriter;

public class StatsSnapshot
{
  public final long averageDownloadSize;
  public final long averageOriginalBitmapSize;
  public final long averageTransformedBitmapSize;
  public final long cacheHits;
  public final long cacheMisses;
  public final long compressedSize;
  public final long downloadCount;
  public final int maxSize;
  public final int originalBitmapCount;
  public final int size;
  public final long totalDownloadSize;
  public final int totalOriginalBitmapSize;
  public final long totalTransformedBitmapSize;
  public final int transformedBitmapCount;
  
  public StatsSnapshot(int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7, long paramLong8, int paramInt3, int paramInt4, int paramInt5, long paramLong9)
  {
    originalBitmapCount = paramInt1;
    transformedBitmapCount = paramInt2;
    totalTransformedBitmapSize = paramLong1;
    averageDownloadSize = paramLong2;
    cacheHits = paramLong3;
    averageOriginalBitmapSize = paramLong4;
    averageTransformedBitmapSize = paramLong5;
    cacheMisses = paramLong6;
    downloadCount = paramLong7;
    totalDownloadSize = paramLong8;
    totalOriginalBitmapSize = paramInt3;
    maxSize = paramInt4;
    size = paramInt5;
    compressedSize = paramLong9;
  }
  
  public void dump(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println("===============BEGIN PICASSO STATS ===============");
    paramPrintWriter.println("Memory Cache Stats");
    paramPrintWriter.print("  Max Cache Size: ");
    paramPrintWriter.println(originalBitmapCount);
    paramPrintWriter.print("  Cache Size: ");
    paramPrintWriter.println(transformedBitmapCount);
    paramPrintWriter.print("  Cache % Full: ");
    paramPrintWriter.println((int)Math.ceil(transformedBitmapCount / originalBitmapCount * 100.0F));
    paramPrintWriter.print("  Cache Hits: ");
    paramPrintWriter.println(totalTransformedBitmapSize);
    paramPrintWriter.print("  Cache Misses: ");
    paramPrintWriter.println(averageDownloadSize);
    paramPrintWriter.println("Network Stats");
    paramPrintWriter.print("  Download Count: ");
    paramPrintWriter.println(totalOriginalBitmapSize);
    paramPrintWriter.print("  Total Download Size: ");
    paramPrintWriter.println(cacheHits);
    paramPrintWriter.print("  Average Download Size: ");
    paramPrintWriter.println(cacheMisses);
    paramPrintWriter.println("Bitmap Stats");
    paramPrintWriter.print("  Total Bitmaps Decoded: ");
    paramPrintWriter.println(maxSize);
    paramPrintWriter.print("  Total Bitmap Size: ");
    paramPrintWriter.println(averageOriginalBitmapSize);
    paramPrintWriter.print("  Total Transformed Bitmaps: ");
    paramPrintWriter.println(size);
    paramPrintWriter.print("  Total Transformed Bitmap Size: ");
    paramPrintWriter.println(averageTransformedBitmapSize);
    paramPrintWriter.print("  Average Bitmap Size: ");
    paramPrintWriter.println(downloadCount);
    paramPrintWriter.print("  Average Transformed Bitmap Size: ");
    paramPrintWriter.println(totalDownloadSize);
    paramPrintWriter.println("===============END PICASSO STATS ===============");
    paramPrintWriter.flush();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("StatsSnapshot{maxSize=");
    localStringBuilder.append(originalBitmapCount);
    localStringBuilder.append(", size=");
    localStringBuilder.append(transformedBitmapCount);
    localStringBuilder.append(", cacheHits=");
    localStringBuilder.append(totalTransformedBitmapSize);
    localStringBuilder.append(", cacheMisses=");
    localStringBuilder.append(averageDownloadSize);
    localStringBuilder.append(", downloadCount=");
    localStringBuilder.append(totalOriginalBitmapSize);
    localStringBuilder.append(", totalDownloadSize=");
    localStringBuilder.append(cacheHits);
    localStringBuilder.append(", averageDownloadSize=");
    localStringBuilder.append(cacheMisses);
    localStringBuilder.append(", totalOriginalBitmapSize=");
    localStringBuilder.append(averageOriginalBitmapSize);
    localStringBuilder.append(", totalTransformedBitmapSize=");
    localStringBuilder.append(averageTransformedBitmapSize);
    localStringBuilder.append(", averageOriginalBitmapSize=");
    localStringBuilder.append(downloadCount);
    localStringBuilder.append(", averageTransformedBitmapSize=");
    localStringBuilder.append(totalDownloadSize);
    localStringBuilder.append(", originalBitmapCount=");
    localStringBuilder.append(maxSize);
    localStringBuilder.append(", transformedBitmapCount=");
    localStringBuilder.append(size);
    localStringBuilder.append(", timeStamp=");
    localStringBuilder.append(compressedSize);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
