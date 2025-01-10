package org.org.adapters;

import a.b.e.a.c;
import a.b.e.a.d;
import android.content.res.AssetManager.AssetInputStream;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class ClassWriter
{
  private static final c[] A;
  private static final c[] B;
  private static final c[] C;
  private static final c[] D;
  static final c[][] E;
  private static final HashMap<String, a.d>[] G;
  private static final HashMap<Integer, a.d>[] H;
  private static final HashSet<String> I;
  private static final c[] K;
  private static final Charset N;
  private static SimpleDateFormat O;
  static final int[] c;
  static final byte[] d;
  static final byte[] g;
  public static final int[] l;
  private static final HashMap<Integer, Integer> m;
  private static final c name;
  public static final int[] o;
  private static final byte[] p;
  private static final byte[] r;
  static final String[] s;
  private static final byte[] t;
  private static final c[] u;
  private static final c[] v;
  private static final c[] w;
  private static final c[] y;
  private static final c[] z;
  private final HashMap<String, a.c>[] a = new HashMap[E.length];
  private ByteOrder b = ByteOrder.BIG_ENDIAN;
  private final AssetManager.AssetInputStream e;
  private final String f;
  private int h;
  private int i;
  private int j;
  private int k;
  private int n;
  private int q;
  private int x;
  
  static
  {
    Integer localInteger1 = Integer.valueOf(1);
    Integer localInteger2 = Integer.valueOf(3);
    Integer localInteger3 = Integer.valueOf(2);
    Integer localInteger4 = Integer.valueOf(8);
    Arrays.asList(new Integer[] { localInteger1, Integer.valueOf(6), localInteger2, localInteger4 });
    Integer localInteger5 = Integer.valueOf(7);
    Integer localInteger6 = Integer.valueOf(5);
    Arrays.asList(new Integer[] { localInteger3, localInteger5, Integer.valueOf(4), localInteger6 });
    l = new int[] { 8, 8, 8 };
    o = new int[] { 8 };
    d = new byte[] { -1, -40, -1 };
    p = new byte[] { 79, 76, 89, 77, 80, 0 };
    r = new byte[] { 79, 76, 89, 77, 80, 85, 83, 0, 73, 73 };
    s = new String[] { "", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE" };
    c = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1 };
    t = new byte[] { 65, 83, 67, 73, 73, 0, 0, 0 };
    u = new c[] { new c("NewSubfileType", 254, 4, null), new c("SubfileType", 255, 4, null), new c("ImageWidth", 256, 3, 4, null), new c("ImageLength", 257, 3, 4, null), new c("BitsPerSample", 258, 3, null), new c("Compression", 259, 3, null), new c("PhotometricInterpretation", 262, 3, null), new c("ImageDescription", 270, 2, null), new c("Make", 271, 2, null), new c("Model", 272, 2, null), new c("StripOffsets", 273, 3, 4, null), new c("Orientation", 274, 3, null), new c("SamplesPerPixel", 277, 3, null), new c("RowsPerStrip", 278, 3, 4, null), new c("StripByteCounts", 279, 3, 4, null), new c("XResolution", 282, 5, null), new c("YResolution", 283, 5, null), new c("PlanarConfiguration", 284, 3, null), new c("ResolutionUnit", 296, 3, null), new c("TransferFunction", 301, 3, null), new c("Software", 305, 2, null), new c("DateTime", 306, 2, null), new c("Artist", 315, 2, null), new c("WhitePoint", 318, 5, null), new c("PrimaryChromaticities", 319, 5, null), new c("SubIFDPointer", 330, 4, null), new c("JPEGInterchangeFormat", 513, 4, null), new c("JPEGInterchangeFormatLength", 514, 4, null), new c("YCbCrCoefficients", 529, 5, null), new c("YCbCrSubSampling", 530, 3, null), new c("YCbCrPositioning", 531, 3, null), new c("ReferenceBlackWhite", 532, 5, null), new c("Copyright", 33432, 2, null), new c("ExifIFDPointer", 34665, 4, null), new c("GPSInfoIFDPointer", 34853, 4, null), new c("SensorTopBorder", 4, 4, null), new c("SensorLeftBorder", 5, 4, null), new c("SensorBottomBorder", 6, 4, null), new c("SensorRightBorder", 7, 4, null), new c("ISO", 23, 3, null), new c("JpgFromRaw", 46, 7, null) };
    v = new c[] { new c("ExposureTime", 33434, 5, null), new c("FNumber", 33437, 5, null), new c("ExposureProgram", 34850, 3, null), new c("SpectralSensitivity", 34852, 2, null), new c("PhotographicSensitivity", 34855, 3, null), new c("OECF", 34856, 7, null), new c("ExifVersion", 36864, 2, null), new c("DateTimeOriginal", 36867, 2, null), new c("DateTimeDigitized", 36868, 2, null), new c("ComponentsConfiguration", 37121, 7, null), new c("CompressedBitsPerPixel", 37122, 5, null), new c("ShutterSpeedValue", 37377, 10, null), new c("ApertureValue", 37378, 5, null), new c("BrightnessValue", 37379, 10, null), new c("ExposureBiasValue", 37380, 10, null), new c("MaxApertureValue", 37381, 5, null), new c("SubjectDistance", 37382, 5, null), new c("MeteringMode", 37383, 3, null), new c("LightSource", 37384, 3, null), new c("Flash", 37385, 3, null), new c("FocalLength", 37386, 5, null), new c("SubjectArea", 37396, 3, null), new c("MakerNote", 37500, 7, null), new c("UserComment", 37510, 7, null), new c("SubSecTime", 37520, 2, null), new c("SubSecTimeOriginal", 37521, 2, null), new c("SubSecTimeDigitized", 37522, 2, null), new c("FlashpixVersion", 40960, 7, null), new c("ColorSpace", 40961, 3, null), new c("PixelXDimension", 40962, 3, 4, null), new c("PixelYDimension", 40963, 3, 4, null), new c("RelatedSoundFile", 40964, 2, null), new c("InteroperabilityIFDPointer", 40965, 4, null), new c("FlashEnergy", 41483, 5, null), new c("SpatialFrequencyResponse", 41484, 7, null), new c("FocalPlaneXResolution", 41486, 5, null), new c("FocalPlaneYResolution", 41487, 5, null), new c("FocalPlaneResolutionUnit", 41488, 3, null), new c("SubjectLocation", 41492, 3, null), new c("ExposureIndex", 41493, 5, null), new c("SensingMethod", 41495, 3, null), new c("FileSource", 41728, 7, null), new c("SceneType", 41729, 7, null), new c("CFAPattern", 41730, 7, null), new c("CustomRendered", 41985, 3, null), new c("ExposureMode", 41986, 3, null), new c("WhiteBalance", 41987, 3, null), new c("DigitalZoomRatio", 41988, 5, null), new c("FocalLengthIn35mmFilm", 41989, 3, null), new c("SceneCaptureType", 41990, 3, null), new c("GainControl", 41991, 3, null), new c("Contrast", 41992, 3, null), new c("Saturation", 41993, 3, null), new c("Sharpness", 41994, 3, null), new c("DeviceSettingDescription", 41995, 7, null), new c("SubjectDistanceRange", 41996, 3, null), new c("ImageUniqueID", 42016, 2, null), new c("DNGVersion", 50706, 1, null), new c("DefaultCropSize", 50720, 3, 4, null) };
    w = new c[] { new c("GPSVersionID", 0, 1, null), new c("GPSLatitudeRef", 1, 2, null), new c("GPSLatitude", 2, 5, null), new c("GPSLongitudeRef", 3, 2, null), new c("GPSLongitude", 4, 5, null), new c("GPSAltitudeRef", 5, 1, null), new c("GPSAltitude", 6, 5, null), new c("GPSTimeStamp", 7, 5, null), new c("GPSSatellites", 8, 2, null), new c("GPSStatus", 9, 2, null), new c("GPSMeasureMode", 10, 2, null), new c("GPSDOP", 11, 5, null), new c("GPSSpeedRef", 12, 2, null), new c("GPSSpeed", 13, 5, null), new c("GPSTrackRef", 14, 2, null), new c("GPSTrack", 15, 5, null), new c("GPSImgDirectionRef", 16, 2, null), new c("GPSImgDirection", 17, 5, null), new c("GPSMapDatum", 18, 2, null), new c("GPSDestLatitudeRef", 19, 2, null), new c("GPSDestLatitude", 20, 5, null), new c("GPSDestLongitudeRef", 21, 2, null), new c("GPSDestLongitude", 22, 5, null), new c("GPSDestBearingRef", 23, 2, null), new c("GPSDestBearing", 24, 5, null), new c("GPSDestDistanceRef", 25, 2, null), new c("GPSDestDistance", 26, 5, null), new c("GPSProcessingMethod", 27, 7, null), new c("GPSAreaInformation", 28, 7, null), new c("GPSDateStamp", 29, 2, null), new c("GPSDifferential", 30, 3, null) };
    z = new c[] { new c("InteroperabilityIndex", 1, 2, null) };
    y = new c[] { new c("NewSubfileType", 254, 4, null), new c("SubfileType", 255, 4, null), new c("ThumbnailImageWidth", 256, 3, 4, null), new c("ThumbnailImageLength", 257, 3, 4, null), new c("BitsPerSample", 258, 3, null), new c("Compression", 259, 3, null), new c("PhotometricInterpretation", 262, 3, null), new c("ImageDescription", 270, 2, null), new c("Make", 271, 2, null), new c("Model", 272, 2, null), new c("StripOffsets", 273, 3, 4, null), new c("Orientation", 274, 3, null), new c("SamplesPerPixel", 277, 3, null), new c("RowsPerStrip", 278, 3, 4, null), new c("StripByteCounts", 279, 3, 4, null), new c("XResolution", 282, 5, null), new c("YResolution", 283, 5, null), new c("PlanarConfiguration", 284, 3, null), new c("ResolutionUnit", 296, 3, null), new c("TransferFunction", 301, 3, null), new c("Software", 305, 2, null), new c("DateTime", 306, 2, null), new c("Artist", 315, 2, null), new c("WhitePoint", 318, 5, null), new c("PrimaryChromaticities", 319, 5, null), new c("SubIFDPointer", 330, 4, null), new c("JPEGInterchangeFormat", 513, 4, null), new c("JPEGInterchangeFormatLength", 514, 4, null), new c("YCbCrCoefficients", 529, 5, null), new c("YCbCrSubSampling", 530, 3, null), new c("YCbCrPositioning", 531, 3, null), new c("ReferenceBlackWhite", 532, 5, null), new c("Copyright", 33432, 2, null), new c("ExifIFDPointer", 34665, 4, null), new c("GPSInfoIFDPointer", 34853, 4, null), new c("DNGVersion", 50706, 1, null), new c("DefaultCropSize", 50720, 3, 4, null) };
    name = new c("StripOffsets", 273, 3, null);
    A = new c[] { new c("ThumbnailImage", 256, 7, null), new c("CameraSettingsIFDPointer", 8224, 4, null), new c("ImageProcessingIFDPointer", 8256, 4, null) };
    B = new c[] { new c("PreviewImageStart", 257, 4, null), new c("PreviewImageLength", 258, 4, null) };
    C = new c[] { new c("AspectFrame", 4371, 3, null) };
    D = new c[] { new c("ColorSpace", 55, 3, null) };
    Object localObject1 = u;
    E = new c[][] { localObject1, v, w, z, y, localObject1, A, B, C, D };
    K = new c[] { new c("SubIFDPointer", 330, 4, null), new c("ExifIFDPointer", 34665, 4, null), new c("GPSInfoIFDPointer", 34853, 4, null), new c("InteroperabilityIFDPointer", 40965, 4, null), new c("CameraSettingsIFDPointer", 8224, 1, null), new c("ImageProcessingIFDPointer", 8256, 1, null) };
    new c("JPEGInterchangeFormat", 513, 4, null);
    new c("JPEGInterchangeFormatLength", 514, 4, null);
    localObject1 = E;
    H = new HashMap[localObject1.length];
    G = new HashMap[localObject1.length];
    I = new HashSet(Arrays.asList(new String[] { "FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp" }));
    m = new HashMap();
    N = Charset.forName("US-ASCII");
    g = "Exif\000\000".getBytes(N);
    O = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    O.setTimeZone(TimeZone.getTimeZone("UTC"));
    int i1 = 0;
    while (i1 < E.length)
    {
      H[i1] = new HashMap();
      G[i1] = new HashMap();
      localObject1 = E[i1];
      int i3 = localObject1.length;
      int i2 = 0;
      while (i2 < i3)
      {
        Object localObject2 = localObject1[i2];
        H[i1].put(Integer.valueOf(b), localObject2);
        G[i1].put(a, localObject2);
        i2 += 1;
      }
      i1 += 1;
    }
    m.put(Integer.valueOf(K0b), localInteger6);
    m.put(Integer.valueOf(K1b), localInteger1);
    m.put(Integer.valueOf(K2b), localInteger3);
    m.put(Integer.valueOf(K3b), localInteger2);
    m.put(Integer.valueOf(K4b), localInteger5);
    m.put(Integer.valueOf(K5b), localInteger4);
    Pattern.compile(".*[1-9].*");
    Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
  }
  
  public ClassWriter(String paramString)
  {
    if (paramString != null)
    {
      String str = null;
      e = null;
      f = paramString;
      try
      {
        paramString = new FileInputStream(paramString);
        str = paramString;
        a(paramString);
        closeSilently(paramString);
        return;
      }
      catch (Throwable paramString)
      {
        closeSilently(str);
        throw paramString;
      }
    }
    throw new IllegalArgumentException("filename cannot be null");
  }
  
  private int a(BufferedInputStream paramBufferedInputStream)
  {
    paramBufferedInputStream.mark(5000);
    byte[] arrayOfByte = new byte['?'];
    paramBufferedInputStream.read(arrayOfByte);
    paramBufferedInputStream.reset();
    if (c(arrayOfByte)) {
      return 4;
    }
    if (encode(arrayOfByte)) {
      return 9;
    }
    if (a(arrayOfByte)) {
      return 7;
    }
    if (put(arrayOfByte)) {
      return 10;
    }
    return 0;
  }
  
  private void a()
  {
    String str = a("DateTimeOriginal");
    if ((str != null) && (a("DateTime") == null)) {
      a[0].put("DateTime", h.a(str));
    }
    if (a("ImageWidth") == null) {
      a[0].put("ImageWidth", h.a(0L, b));
    }
    if (a("ImageLength") == null) {
      a[0].put("ImageLength", h.a(0L, b));
    }
    if (a("Orientation") == null) {
      a[0].put("Orientation", h.a(0L, b));
    }
    if (a("LightSource") == null) {
      a[1].put("LightSource", h.a(0L, b));
    }
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    if (!a[paramInt1].isEmpty())
    {
      if (a[paramInt2].isEmpty()) {
        return;
      }
      Object localObject = (h)a[paramInt1].get("ImageLength");
      h localH1 = (h)a[paramInt1].get("ImageWidth");
      h localH2 = (h)a[paramInt2].get("ImageLength");
      h localH3 = (h)a[paramInt2].get("ImageWidth");
      if (localObject != null)
      {
        if (localH1 == null) {
          return;
        }
        if (localH2 != null)
        {
          if (localH3 == null) {
            return;
          }
          int i1 = ((h)localObject).b(b);
          int i2 = localH1.b(b);
          int i3 = localH2.b(b);
          int i4 = localH3.b(b);
          if ((i1 < i3) && (i2 < i4))
          {
            localObject = a;
            localH1 = localObject[paramInt1];
            localObject[paramInt1] = localObject[paramInt2];
            localObject[paramInt2] = localH1;
          }
        }
      }
    }
  }
  
  private void a(InputStream paramInputStream)
  {
    int i1 = 0;
    for (;;)
    {
      try
      {
        int i2 = E.length;
        if (i1 < i2) {
          arrayOfHashMap = a;
        }
      }
      catch (Throwable paramInputStream)
      {
        HashMap[] arrayOfHashMap;
        HashMap localHashMap;
        a();
        throw paramInputStream;
      }
      try
      {
        localHashMap = new HashMap();
        arrayOfHashMap[i1] = localHashMap;
        i1 += 1;
      }
      catch (IOException paramInputStream)
      {
        a();
      }
    }
    paramInputStream = new BufferedInputStream(paramInputStream, 5000);
    i1 = a(paramInputStream);
    i = i1;
    paramInputStream = new SmbRandomAccessFile(paramInputStream);
    i1 = i;
    switch (i1)
    {
    default: 
      break;
    case 10: 
      f(paramInputStream);
      break;
    case 9: 
      read(paramInputStream);
      break;
    case 7: 
      a(paramInputStream);
      break;
    case 4: 
      a(paramInputStream, 0, 0);
      break;
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 5: 
    case 6: 
    case 8: 
    case 11: 
      b(paramInputStream);
    }
    c(paramInputStream);
  }
  
  private void a(SmbRandomAccessFile paramSmbRandomAccessFile)
  {
    b(paramSmbRandomAccessFile);
    paramSmbRandomAccessFile = (h)a[1].get("MakerNote");
    if (paramSmbRandomAccessFile != null)
    {
      paramSmbRandomAccessFile = new SmbRandomAccessFile(d);
      paramSmbRandomAccessFile.write(b);
      Object localObject = new byte[p.length];
      paramSmbRandomAccessFile.readFully((byte[])localObject);
      paramSmbRandomAccessFile.write(0L);
      byte[] arrayOfByte = new byte[r.length];
      paramSmbRandomAccessFile.readFully(arrayOfByte);
      if (Arrays.equals((byte[])localObject, p)) {
        paramSmbRandomAccessFile.write(8L);
      } else if (Arrays.equals(arrayOfByte, r)) {
        paramSmbRandomAccessFile.write(12L);
      }
      a(paramSmbRandomAccessFile, 6);
      paramSmbRandomAccessFile = (h)a[7].get("PreviewImageStart");
      localObject = (h)a[7].get("PreviewImageLength");
      if ((paramSmbRandomAccessFile != null) && (localObject != null))
      {
        a[5].put("JPEGInterchangeFormat", paramSmbRandomAccessFile);
        a[5].put("JPEGInterchangeFormatLength", localObject);
      }
      paramSmbRandomAccessFile = (h)a[8].get("AspectFrame");
      if (paramSmbRandomAccessFile != null)
      {
        paramSmbRandomAccessFile = (int[])h.b(paramSmbRandomAccessFile, b);
        if ((paramSmbRandomAccessFile != null) && (paramSmbRandomAccessFile.length == 4))
        {
          if ((paramSmbRandomAccessFile[2] > paramSmbRandomAccessFile[0]) && (paramSmbRandomAccessFile[3] > paramSmbRandomAccessFile[1]))
          {
            int i4 = paramSmbRandomAccessFile[2] - paramSmbRandomAccessFile[0] + 1;
            int i3 = paramSmbRandomAccessFile[3] - paramSmbRandomAccessFile[1] + 1;
            int i2 = i4;
            int i1 = i3;
            if (i4 < i3)
            {
              i2 = i4 + i3;
              i1 = i2 - i3;
              i2 -= i1;
            }
            paramSmbRandomAccessFile = h.a(i2, b);
            localObject = h.a(i1, b);
            a[0].put("ImageWidth", paramSmbRandomAccessFile);
            a[0].put("ImageLength", localObject);
          }
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Invalid aspect frame values. frame=");
          ((StringBuilder)localObject).append(Arrays.toString(paramSmbRandomAccessFile));
          Log.w("ExifInterface", ((StringBuilder)localObject).toString());
        }
      }
    }
  }
  
  private void a(SmbRandomAccessFile paramSmbRandomAccessFile, int paramInt)
  {
    if (SmbRandomAccessFile.write(paramSmbRandomAccessFile) + 2 > SmbRandomAccessFile.read(paramSmbRandomAccessFile)) {
      return;
    }
    int i6 = paramSmbRandomAccessFile.readShort();
    if (SmbRandomAccessFile.write(paramSmbRandomAccessFile) + i6 * 12 > SmbRandomAccessFile.read(paramSmbRandomAccessFile)) {
      return;
    }
    for (int i2 = 0;; i2 = (short)(i2 + 1))
    {
      int i4 = paramInt;
      if (i2 >= i6) {
        break;
      }
      int i7 = paramSmbRandomAccessFile.readUnsignedShort();
      int i5 = paramSmbRandomAccessFile.readUnsignedShort();
      int i1 = i5;
      int i8 = paramSmbRandomAccessFile.readInt();
      long l2 = paramSmbRandomAccessFile.write() + 4L;
      Object localObject1 = (c)H[i4].get(Integer.valueOf(i7));
      int i3 = 0;
      Object localObject2;
      if (localObject1 == null)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Skip the tag entry since tag number is not defined: ");
        ((StringBuilder)localObject2).append(i7);
        Log.w("ExifInterface", ((StringBuilder)localObject2).toString());
      }
      else if ((i5 > 0) && (i5 < c.length))
      {
        if (!c.a((c)localObject1, i5))
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Skip the tag entry since data format (");
          ((StringBuilder)localObject2).append(s[i5]);
          ((StringBuilder)localObject2).append(") is unexpected for tag: ");
          ((StringBuilder)localObject2).append(a);
          Log.w("ExifInterface", ((StringBuilder)localObject2).toString());
        }
        else
        {
          if (i5 == 7) {
            i1 = d;
          }
          l1 = i8 * c[i1];
          if ((l1 >= 0L) && (l1 <= 2147483647L))
          {
            i3 = 1;
            break label375;
          }
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Skip the tag entry since the number of components is invalid: ");
          ((StringBuilder)localObject2).append(i8);
          Log.w("ExifInterface", ((StringBuilder)localObject2).toString());
          break label375;
        }
      }
      else
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Skip the tag entry since data format is invalid: ");
        ((StringBuilder)localObject2).append(i5);
        Log.w("ExifInterface", ((StringBuilder)localObject2).toString());
      }
      long l1 = 0L;
      label375:
      if (i3 == 0)
      {
        paramSmbRandomAccessFile.write(l2);
      }
      else
      {
        if (l1 > 4L)
        {
          i3 = paramSmbRandomAccessFile.readInt();
          i5 = i;
          if (i5 == 7)
          {
            if ("MakerNote".equals(a)) {
              q = i3;
            } else if (i4 == 6) {
              if ("ThumbnailImage".equals(a))
              {
                j = i3;
                n = i8;
                localObject2 = h.a(6, b);
                h localH1 = h.a(j, b);
                h localH2 = h.a(n, b);
                a[4].put("Compression", localObject2);
                a[4].put("JPEGInterchangeFormat", localH1);
                a[4].put("JPEGInterchangeFormatLength", localH2);
                break label589;
              }
            }
          }
          else if ((i5 == 10) && ("JpgFromRaw".equals(a))) {
            k = i3;
          }
          label589:
          if (i3 + l1 <= SmbRandomAccessFile.read(paramSmbRandomAccessFile))
          {
            paramSmbRandomAccessFile.write(i3);
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Skip the tag entry since data offset is invalid: ");
            ((StringBuilder)localObject1).append(i3);
            Log.w("ExifInterface", ((StringBuilder)localObject1).toString());
            paramSmbRandomAccessFile.write(l2);
            continue;
          }
        }
        localObject2 = (Integer)m.get(Integer.valueOf(i7));
        if (localObject2 != null)
        {
          l1 = -1L;
          if (i1 != 3)
          {
            if (i1 != 4)
            {
              if (i1 != 8)
              {
                if ((i1 == 9) || (i1 == 13)) {
                  l1 = paramSmbRandomAccessFile.readInt();
                }
              }
              else {
                l1 = paramSmbRandomAccessFile.readShort();
              }
            }
            else {
              l1 = paramSmbRandomAccessFile.length();
            }
          }
          else {
            l1 = paramSmbRandomAccessFile.readUnsignedShort();
          }
          if ((l1 > 0L) && (l1 < SmbRandomAccessFile.read(paramSmbRandomAccessFile)))
          {
            paramSmbRandomAccessFile.write(l1);
            a(paramSmbRandomAccessFile, ((Integer)localObject2).intValue());
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Skip jump into the IFD since its offset is invalid: ");
            ((StringBuilder)localObject1).append(l1);
            Log.w("ExifInterface", ((StringBuilder)localObject1).toString());
          }
          paramSmbRandomAccessFile.write(l2);
        }
        else
        {
          localObject2 = new byte[(int)l1];
          paramSmbRandomAccessFile.readFully((byte[])localObject2);
          localObject2 = new h(i1, i8, (byte[])localObject2, null);
          a[i4].put(a, localObject2);
          if ("DNGVersion".equals(a)) {
            i = 3;
          }
          if (((!"Make".equals(a)) && (!"Model".equals(a))) || ((((h)localObject2).write(b).contains("PENTAX")) || (("Compression".equals(a)) && (((h)localObject2).b(b) == 65535)))) {
            i = 8;
          }
          if (paramSmbRandomAccessFile.write() != l2) {
            paramSmbRandomAccessFile.write(l2);
          }
        }
      }
    }
    if (paramSmbRandomAccessFile.write() + 4 <= SmbRandomAccessFile.read(paramSmbRandomAccessFile))
    {
      paramInt = paramSmbRandomAccessFile.readInt();
      if ((paramInt > 8) && (paramInt < SmbRandomAccessFile.read(paramSmbRandomAccessFile)))
      {
        paramSmbRandomAccessFile.write(paramInt);
        if (a[4].isEmpty())
        {
          a(paramSmbRandomAccessFile, 4);
          return;
        }
        if (a[5].isEmpty()) {
          a(paramSmbRandomAccessFile, 5);
        }
      }
    }
  }
  
  private void a(SmbRandomAccessFile paramSmbRandomAccessFile, int paramInt1, int paramInt2)
  {
    paramSmbRandomAccessFile.write(ByteOrder.BIG_ENDIAN);
    paramSmbRandomAccessFile.write(paramInt1);
    int i1 = paramSmbRandomAccessFile.readByte();
    if (i1 == -1)
    {
      if (paramSmbRandomAccessFile.readByte() == -40)
      {
        for (paramInt1 = paramInt1 + 1 + 1;; paramInt1 = i1 + paramInt1)
        {
          i1 = paramSmbRandomAccessFile.readByte();
          if (i1 != -1) {
            break label652;
          }
          i1 = paramSmbRandomAccessFile.readByte();
          if ((i1 == -39) || (i1 == -38)) {
            break label643;
          }
          int i3 = paramSmbRandomAccessFile.readUnsignedShort() - 2;
          int i2 = paramInt1 + 1 + 1 + 2;
          if (i3 < 0) {
            break label632;
          }
          byte[] arrayOfByte;
          if (i1 != -31)
          {
            if (i1 != -2)
            {
              switch (i1)
              {
              default: 
                switch (i1)
                {
                default: 
                  switch (i1)
                  {
                  default: 
                    switch (i1)
                    {
                    default: 
                      i1 = i2;
                      paramInt1 = i3;
                    }
                    break;
                  }
                  break;
                }
                break;
              case -64: 
              case -63: 
              case -62: 
              case -61: 
                if (paramSmbRandomAccessFile.skipBytes(1) == 1)
                {
                  a[paramInt2].put("ImageLength", h.a(paramSmbRandomAccessFile.readUnsignedShort(), b));
                  a[paramInt2].put("ImageWidth", h.a(paramSmbRandomAccessFile.readUnsignedShort(), b));
                  paramInt1 = i3 - 5;
                  i1 = i2;
                  break;
                }
                throw new IOException("Invalid SOFx");
              }
            }
            else
            {
              arrayOfByte = new byte[i3];
              if (paramSmbRandomAccessFile.read(arrayOfByte) == i3)
              {
                i3 = 0;
                i1 = i2;
                paramInt1 = i3;
                if (a("UserComment") == null)
                {
                  a[1].put("UserComment", h.a(new String(arrayOfByte, N)));
                  i1 = i2;
                  paramInt1 = i3;
                }
              }
              else
              {
                throw new IOException("Invalid exif");
              }
            }
          }
          else if (i3 < 6)
          {
            i1 = i2;
            paramInt1 = i3;
          }
          else
          {
            arrayOfByte = new byte[6];
            if (paramSmbRandomAccessFile.read(arrayOfByte) != 6) {
              break label621;
            }
            i1 = i2 + 6;
            paramInt1 = i3 - 6;
            if (Arrays.equals(arrayOfByte, g))
            {
              if (paramInt1 <= 0) {
                break label610;
              }
              h = i1;
              arrayOfByte = new byte[paramInt1];
              if (paramSmbRandomAccessFile.read(arrayOfByte) != paramInt1) {
                break label599;
              }
              i1 += paramInt1;
              paramInt1 = 0;
              a(arrayOfByte, paramInt2);
            }
          }
          if (paramInt1 < 0) {
            break label588;
          }
          if (paramSmbRandomAccessFile.skipBytes(paramInt1) != paramInt1) {
            break;
          }
        }
        throw new IOException("Invalid JPEG segment");
        label588:
        throw new IOException("Invalid length");
        label599:
        throw new IOException("Invalid exif");
        label610:
        throw new IOException("Invalid exif");
        label621:
        throw new IOException("Invalid exif");
        label632:
        throw new IOException("Invalid length");
        label643:
        paramSmbRandomAccessFile.write(b);
        return;
        label652:
        paramSmbRandomAccessFile = new StringBuilder();
        paramSmbRandomAccessFile.append("Invalid marker:");
        paramSmbRandomAccessFile.append(Integer.toHexString(i1 & 0xFF));
        throw new IOException(paramSmbRandomAccessFile.toString());
      }
      paramSmbRandomAccessFile = new StringBuilder();
      paramSmbRandomAccessFile.append("Invalid marker: ");
      paramSmbRandomAccessFile.append(Integer.toHexString(i1 & 0xFF));
      throw new IOException(paramSmbRandomAccessFile.toString());
    }
    paramSmbRandomAccessFile = new StringBuilder();
    paramSmbRandomAccessFile.append("Invalid marker: ");
    paramSmbRandomAccessFile.append(Integer.toHexString(i1 & 0xFF));
    paramSmbRandomAccessFile = new IOException(paramSmbRandomAccessFile.toString());
    throw paramSmbRandomAccessFile;
  }
  
  private void a(SmbRandomAccessFile paramSmbRandomAccessFile, HashMap paramHashMap)
  {
    Object localObject1 = (h)paramHashMap.get("StripOffsets");
    Object localObject2 = (h)paramHashMap.get("StripByteCounts");
    if ((localObject1 != null) && (localObject2 != null))
    {
      paramHashMap = toArray(h.b((h)localObject1, b));
      localObject1 = toArray(h.b((h)localObject2, b));
      if (paramHashMap == null)
      {
        Log.w("ExifInterface", "stripOffsets should not be null.");
        return;
      }
      if (localObject1 == null)
      {
        Log.w("ExifInterface", "stripByteCounts should not be null.");
        return;
      }
      int i2 = localObject1.length;
      long l1 = 0L;
      int i1 = 0;
      while (i1 < i2)
      {
        l1 += localObject1[i1];
        i1 += 1;
      }
      localObject2 = new byte[(int)l1];
      int i3 = 0;
      i2 = 0;
      i1 = 0;
      while (i1 < paramHashMap.length)
      {
        int i5 = (int)paramHashMap[i1];
        int i4 = (int)localObject1[i1];
        i5 -= i3;
        if (i5 < 0) {
          Log.d("ExifInterface", "Invalid strip offset value");
        }
        paramSmbRandomAccessFile.write(i5);
        byte[] arrayOfByte = new byte[i4];
        paramSmbRandomAccessFile.read(arrayOfByte);
        i3 = i3 + i5 + i4;
        System.arraycopy(arrayOfByte, 0, localObject2, i2, arrayOfByte.length);
        i2 += arrayOfByte.length;
        i1 += 1;
      }
    }
  }
  
  private void a(byte[] paramArrayOfByte, int paramInt)
  {
    SmbRandomAccessFile localSmbRandomAccessFile = new SmbRandomAccessFile(paramArrayOfByte);
    read(localSmbRandomAccessFile, paramArrayOfByte.length);
    a(localSmbRandomAccessFile, paramInt);
  }
  
  private boolean a(HashMap paramHashMap)
  {
    h localH = (h)paramHashMap.get("ImageLength");
    paramHashMap = (h)paramHashMap.get("ImageWidth");
    if ((localH != null) && (paramHashMap != null))
    {
      int i1 = localH.b(b);
      int i2 = paramHashMap.b(b);
      if ((i1 <= 512) && (i2 <= 512)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean a(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = new SmbRandomAccessFile(paramArrayOfByte);
    b = readHeader(paramArrayOfByte);
    paramArrayOfByte.write(b);
    int i1 = paramArrayOfByte.readShort();
    paramArrayOfByte.close();
    return (i1 == 20306) || (i1 == 21330);
  }
  
  private h b(String paramString)
  {
    String str = paramString;
    if ("ISOSpeedRatings".equals(paramString)) {
      str = "PhotographicSensitivity";
    }
    int i1 = 0;
    while (i1 < E.length)
    {
      paramString = (h)a[i1].get(str);
      if (paramString != null) {
        return paramString;
      }
      i1 += 1;
    }
    return null;
  }
  
  private void b(SmbRandomAccessFile paramSmbRandomAccessFile)
  {
    read(paramSmbRandomAccessFile, paramSmbRandomAccessFile.available());
    a(paramSmbRandomAccessFile, 0);
    c(paramSmbRandomAccessFile, 0);
    c(paramSmbRandomAccessFile, 5);
    c(paramSmbRandomAccessFile, 4);
    f(paramSmbRandomAccessFile);
    if (i == 8)
    {
      paramSmbRandomAccessFile = (h)a[1].get("MakerNote");
      if (paramSmbRandomAccessFile != null)
      {
        paramSmbRandomAccessFile = new SmbRandomAccessFile(d);
        paramSmbRandomAccessFile.write(b);
        paramSmbRandomAccessFile.write(6L);
        a(paramSmbRandomAccessFile, 9);
        paramSmbRandomAccessFile = (h)a[9].get("ColorSpace");
        if (paramSmbRandomAccessFile != null) {
          a[1].put("ColorSpace", paramSmbRandomAccessFile);
        }
      }
    }
  }
  
  private void b(SmbRandomAccessFile paramSmbRandomAccessFile, int paramInt)
  {
    h localH1 = (h)a[paramInt].get("ImageLength");
    h localH2 = (h)a[paramInt].get("ImageWidth");
    if ((localH1 == null) || (localH2 == null))
    {
      localH1 = (h)a[paramInt].get("JPEGInterchangeFormat");
      if (localH1 != null) {
        a(paramSmbRandomAccessFile, localH1.b(b), paramInt);
      }
    }
  }
  
  private void b(SmbRandomAccessFile paramSmbRandomAccessFile, HashMap paramHashMap)
  {
    h localH = (h)paramHashMap.get("JPEGInterchangeFormat");
    paramHashMap = (h)paramHashMap.get("JPEGInterchangeFormatLength");
    if ((localH != null) && (paramHashMap != null))
    {
      int i2 = localH.b(b);
      int i1 = i2;
      int i3 = Math.min(paramHashMap.b(b), paramSmbRandomAccessFile.available() - i2);
      int i4 = i;
      if ((i4 != 4) && (i4 != 9) && (i4 != 10))
      {
        if (i4 == 7) {
          i1 = i2 + q;
        }
      }
      else {
        i1 = i2 + h;
      }
      if ((i1 > 0) && (i3 > 0) && (f == null) && (e == null))
      {
        paramHashMap = new byte[i3];
        paramSmbRandomAccessFile.write(i1);
        paramSmbRandomAccessFile.readFully(paramHashMap);
      }
    }
  }
  
  private boolean b(HashMap paramHashMap)
  {
    Object localObject = (h)paramHashMap.get("BitsPerSample");
    if (localObject != null)
    {
      localObject = (int[])h.b((h)localObject, b);
      if (Arrays.equals(l, (int[])localObject)) {
        return true;
      }
      if (i == 3)
      {
        paramHashMap = (h)paramHashMap.get("PhotometricInterpretation");
        if (paramHashMap != null)
        {
          int i1 = paramHashMap.b(b);
          if ((i1 == 1) && (Arrays.equals((int[])localObject, o))) {
            break label104;
          }
          if ((i1 == 6) && (Arrays.equals((int[])localObject, l))) {
            return true;
          }
        }
      }
    }
    return false;
    label104:
    return true;
  }
  
  private void c(SmbRandomAccessFile paramSmbRandomAccessFile)
  {
    HashMap localHashMap = a[4];
    h localH = (h)localHashMap.get("Compression");
    if (localH != null)
    {
      x = localH.b(b);
      int i1 = x;
      if (i1 != 1) {
        if (i1 != 6)
        {
          if (i1 == 7) {}
        }
        else
        {
          b(paramSmbRandomAccessFile, localHashMap);
          return;
        }
      }
      if (b(localHashMap)) {
        a(paramSmbRandomAccessFile, localHashMap);
      }
      return;
    }
    x = 6;
    b(paramSmbRandomAccessFile, localHashMap);
  }
  
  private void c(SmbRandomAccessFile paramSmbRandomAccessFile, int paramInt)
  {
    Object localObject = (h)a[paramInt].get("DefaultCropSize");
    h localH1 = (h)a[paramInt].get("SensorTopBorder");
    h localH2 = (h)a[paramInt].get("SensorLeftBorder");
    h localH3 = (h)a[paramInt].get("SensorBottomBorder");
    h localH4 = (h)a[paramInt].get("SensorRightBorder");
    if (localObject != null)
    {
      if (c == 5)
      {
        localObject = (Item[])h.b((h)localObject, b);
        if ((localObject != null) && (localObject.length == 2))
        {
          paramSmbRandomAccessFile = h.a(localObject[0], b);
          localObject = h.a(localObject[1], b);
        }
        else
        {
          paramSmbRandomAccessFile = new StringBuilder();
          paramSmbRandomAccessFile.append("Invalid crop size values. cropSize=");
          paramSmbRandomAccessFile.append(Arrays.toString((Object[])localObject));
          Log.w("ExifInterface", paramSmbRandomAccessFile.toString());
        }
      }
      else
      {
        localObject = (int[])h.b((h)localObject, b);
        if ((localObject == null) || (localObject.length != 2)) {
          break label272;
        }
        paramSmbRandomAccessFile = h.a(localObject[0], b);
        localObject = h.a(localObject[1], b);
      }
      a[paramInt].put("ImageWidth", paramSmbRandomAccessFile);
      a[paramInt].put("ImageLength", localObject);
      return;
      label272:
      paramSmbRandomAccessFile = new StringBuilder();
      paramSmbRandomAccessFile.append("Invalid crop size values. cropSize=");
      paramSmbRandomAccessFile.append(Arrays.toString((int[])localObject));
      Log.w("ExifInterface", paramSmbRandomAccessFile.toString());
      return;
    }
    if ((localH1 != null) && (localH2 != null) && (localH3 != null) && (localH4 != null))
    {
      int i1 = localH1.b(b);
      int i2 = localH3.b(b);
      int i3 = localH4.b(b);
      int i4 = localH2.b(b);
      if ((i2 > i1) && (i3 > i4))
      {
        paramSmbRandomAccessFile = h.a(i2 - i1, b);
        localObject = h.a(i3 - i4, b);
        a[paramInt].put("ImageLength", paramSmbRandomAccessFile);
        a[paramInt].put("ImageWidth", localObject);
        return;
      }
      return;
    }
    b(paramSmbRandomAccessFile, paramInt);
  }
  
  private static boolean c(byte[] paramArrayOfByte)
  {
    int i1 = 0;
    for (;;)
    {
      byte[] arrayOfByte = d;
      if (i1 >= arrayOfByte.length) {
        break;
      }
      if (paramArrayOfByte[i1] != arrayOfByte[i1]) {
        return false;
      }
      i1 += 1;
    }
    return true;
  }
  
  private static void closeSilently(Closeable paramCloseable)
  {
    if (paramCloseable != null) {
      try
      {
        paramCloseable.close();
        return;
      }
      catch (Exception paramCloseable) {}catch (RuntimeException paramCloseable)
      {
        throw paramCloseable;
      }
    }
  }
  
  private boolean encode(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
    int i1 = 0;
    while (i1 < arrayOfByte.length)
    {
      if (paramArrayOfByte[i1] != arrayOfByte[i1]) {
        return false;
      }
      i1 += 1;
    }
    return true;
  }
  
  private void f(InputStream paramInputStream)
  {
    a(0, 5);
    a(0, 4);
    a(5, 4);
    paramInputStream = (h)a[1].get("PixelXDimension");
    h localH = (h)a[1].get("PixelYDimension");
    if ((paramInputStream != null) && (localH != null))
    {
      a[0].put("ImageWidth", paramInputStream);
      a[0].put("ImageLength", localH);
    }
    if ((a[4].isEmpty()) && (a(a[5])))
    {
      paramInputStream = a;
      paramInputStream[4] = paramInputStream[5];
      paramInputStream[5] = new HashMap();
    }
    if (!a(a[4])) {
      Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
    }
  }
  
  private void f(SmbRandomAccessFile paramSmbRandomAccessFile)
  {
    b(paramSmbRandomAccessFile);
    if ((h)a[0].get("JpgFromRaw") != null) {
      a(paramSmbRandomAccessFile, k, 5);
    }
    paramSmbRandomAccessFile = (h)a[0].get("ISO");
    h localH = (h)a[1].get("PhotographicSensitivity");
    if ((paramSmbRandomAccessFile != null) && (localH == null)) {
      a[1].put("PhotographicSensitivity", paramSmbRandomAccessFile);
    }
  }
  
  private boolean put(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = new SmbRandomAccessFile(paramArrayOfByte);
    b = readHeader(paramArrayOfByte);
    paramArrayOfByte.write(b);
    int i1 = paramArrayOfByte.readShort();
    paramArrayOfByte.close();
    return i1 == 85;
  }
  
  private void read(SmbRandomAccessFile paramSmbRandomAccessFile)
  {
    paramSmbRandomAccessFile.skipBytes(84);
    Object localObject = new byte[4];
    byte[] arrayOfByte = new byte[4];
    paramSmbRandomAccessFile.read((byte[])localObject);
    paramSmbRandomAccessFile.skipBytes(4);
    paramSmbRandomAccessFile.read(arrayOfByte);
    int i1 = ByteBuffer.wrap((byte[])localObject).getInt();
    int i2 = ByteBuffer.wrap(arrayOfByte).getInt();
    a(paramSmbRandomAccessFile, i1, 5);
    paramSmbRandomAccessFile.write(i2);
    paramSmbRandomAccessFile.write(ByteOrder.BIG_ENDIAN);
    i2 = paramSmbRandomAccessFile.readInt();
    i1 = 0;
    while (i1 < i2)
    {
      int i3 = paramSmbRandomAccessFile.readUnsignedShort();
      int i4 = paramSmbRandomAccessFile.readUnsignedShort();
      if (i3 == nameb)
      {
        i1 = paramSmbRandomAccessFile.readShort();
        i2 = paramSmbRandomAccessFile.readShort();
        paramSmbRandomAccessFile = h.a(i1, b);
        localObject = h.a(i2, b);
        a[0].put("ImageLength", paramSmbRandomAccessFile);
        a[0].put("ImageWidth", localObject);
        return;
      }
      paramSmbRandomAccessFile.skipBytes(i4);
      i1 += 1;
    }
  }
  
  private void read(SmbRandomAccessFile paramSmbRandomAccessFile, int paramInt)
  {
    b = readHeader(paramSmbRandomAccessFile);
    paramSmbRandomAccessFile.write(b);
    int i1 = paramSmbRandomAccessFile.readUnsignedShort();
    int i2 = i;
    if ((i2 != 7) && (i2 != 10) && (i1 != 42))
    {
      paramSmbRandomAccessFile = new StringBuilder();
      paramSmbRandomAccessFile.append("Invalid start code: ");
      paramSmbRandomAccessFile.append(Integer.toHexString(i1));
      throw new IOException(paramSmbRandomAccessFile.toString());
    }
    i1 = paramSmbRandomAccessFile.readInt();
    if ((i1 >= 8) && (i1 < paramInt))
    {
      paramInt = i1 - 8;
      if (paramInt > 0)
      {
        if (paramSmbRandomAccessFile.skipBytes(paramInt) == paramInt) {
          return;
        }
        paramSmbRandomAccessFile = new StringBuilder();
        paramSmbRandomAccessFile.append("Couldn't jump to first Ifd: ");
        paramSmbRandomAccessFile.append(paramInt);
        throw new IOException(paramSmbRandomAccessFile.toString());
      }
    }
    else
    {
      paramSmbRandomAccessFile = new StringBuilder();
      paramSmbRandomAccessFile.append("Invalid first Ifd offset: ");
      paramSmbRandomAccessFile.append(i1);
      throw new IOException(paramSmbRandomAccessFile.toString());
    }
  }
  
  private ByteOrder readHeader(SmbRandomAccessFile paramSmbRandomAccessFile)
  {
    int i1 = paramSmbRandomAccessFile.readShort();
    if (i1 != 18761)
    {
      if (i1 == 19789) {
        return ByteOrder.BIG_ENDIAN;
      }
      paramSmbRandomAccessFile = new StringBuilder();
      paramSmbRandomAccessFile.append("Invalid byte order: ");
      paramSmbRandomAccessFile.append(Integer.toHexString(i1));
      throw new IOException(paramSmbRandomAccessFile.toString());
    }
    return ByteOrder.LITTLE_ENDIAN;
  }
  
  private static long[] toArray(Object paramObject)
  {
    if ((paramObject instanceof int[]))
    {
      paramObject = (int[])paramObject;
      long[] arrayOfLong = new long[paramObject.length];
      int i1 = 0;
      while (i1 < paramObject.length)
      {
        arrayOfLong[i1] = paramObject[i1];
        i1 += 1;
      }
      return arrayOfLong;
    }
    if ((paramObject instanceof long[])) {
      return (long[])paramObject;
    }
    return null;
  }
  
  public int a(String paramString, int paramInt)
  {
    paramString = b(paramString);
    if (paramString == null) {
      return paramInt;
    }
    ByteOrder localByteOrder = b;
    try
    {
      int i1 = paramString.b(localByteOrder);
      return i1;
    }
    catch (NumberFormatException paramString) {}
    return paramInt;
  }
  
  public String a(String paramString)
  {
    Object localObject = b(paramString);
    if (localObject != null)
    {
      if (!I.contains(paramString)) {
        return ((h)localObject).write(b);
      }
      if (paramString.equals("GPSTimeStamp"))
      {
        int i1 = c;
        if ((i1 != 5) && (i1 != 10))
        {
          paramString = new StringBuilder();
          paramString.append("GPS Timestamp format is not rational. format=");
          paramString.append(c);
          Log.w("ExifInterface", paramString.toString());
          return null;
        }
        paramString = (Item[])h.b((h)localObject, b);
        if ((paramString != null) && (paramString.length == 3)) {
          return String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf((int)((float)0a / (float)0b)), Integer.valueOf((int)((float)1a / (float)1b)), Integer.valueOf((int)((float)2a / (float)2b)) });
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Invalid GPS Timestamp array. array=");
        ((StringBuilder)localObject).append(Arrays.toString(paramString));
        Log.w("ExifInterface", ((StringBuilder)localObject).toString());
        return null;
      }
      paramString = b;
      try
      {
        paramString = Double.toString(((h)localObject).add(paramString));
        return paramString;
      }
      catch (NumberFormatException paramString) {}
    }
    return null;
  }
}
