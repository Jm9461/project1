package okhttp.internal.httpclient;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import okhttp.internal.Util;

public final class HttpDate
{
  private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length];
  private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
  private static final ThreadLocal<DateFormat> STANDARD_DATE_FORMAT = new BitmapHunter.1();
  
  static
  {
    BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = new String[] { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z" };
  }
  
  public static String format(Date paramDate)
  {
    return ((DateFormat)STANDARD_DATE_FORMAT.get()).format(paramDate);
  }
  
  public static Date parse(String paramString)
  {
    if (paramString.length() == 0) {
      return null;
    }
    ParsePosition localParsePosition = new ParsePosition(0);
    Object localObject1 = ((DateFormat)STANDARD_DATE_FORMAT.get()).parse(paramString, localParsePosition);
    if (localParsePosition.getIndex() == paramString.length()) {
      return localObject1;
    }
    String[] arrayOfString = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
    int i = 0;
    try
    {
      int j = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length;
      while (i < j)
      {
        Object localObject2 = BROWSER_COMPATIBLE_DATE_FORMATS[i];
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject2 = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[i], Locale.US);
          localObject1 = localObject2;
          ((DateFormat)localObject2).setTimeZone(Util.UTC);
          BROWSER_COMPATIBLE_DATE_FORMATS[i] = localObject2;
        }
        localParsePosition.setIndex(0);
        localObject1 = ((DateFormat)localObject1).parse(paramString, localParsePosition);
        if (localParsePosition.getIndex() != 0) {
          return localObject1;
        }
        i += 1;
      }
      return null;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
}
