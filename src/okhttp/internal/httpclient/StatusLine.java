package okhttp.internal.httpclient;

import java.net.ProtocolException;
import okhttp.Protocol;

public final class StatusLine
{
  public final int code;
  public final String message;
  public final Protocol protocol;
  
  public StatusLine(Protocol paramProtocol, int paramInt, String paramString)
  {
    protocol = paramProtocol;
    code = paramInt;
    message = paramString;
  }
  
  public static StatusLine parse(String paramString)
  {
    int j;
    int i;
    Object localObject;
    if (paramString.startsWith("HTTP/1."))
    {
      if ((paramString.length() >= 9) && (paramString.charAt(8) == ' '))
      {
        j = paramString.charAt(7) - '0';
        i = 9;
        if (j == 0)
        {
          localObject = Protocol.HTTP_1_0;
        }
        else
        {
          if (j != 1) {
            break label65;
          }
          localObject = Protocol.HTTP_1_1;
        }
        break label146;
        label65:
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Unexpected status line: ");
        ((StringBuilder)localObject).append(paramString);
        throw new ProtocolException(((StringBuilder)localObject).toString());
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Unexpected status line: ");
        ((StringBuilder)localObject).append(paramString);
        throw new ProtocolException(((StringBuilder)localObject).toString());
      }
    }
    else
    {
      if (!paramString.startsWith("ICY ")) {
        break label318;
      }
      localObject = Protocol.HTTP_1_0;
      i = 4;
    }
    label146:
    if (paramString.length() >= i + 3) {
      try
      {
        j = Integer.parseInt(paramString.substring(i, i + 3));
        String str = "";
        if (paramString.length() > i + 3) {
          if (paramString.charAt(i + 3) == ' ')
          {
            str = paramString.substring(i + 4);
          }
          else
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Unexpected status line: ");
            ((StringBuilder)localObject).append(paramString);
            throw new ProtocolException(((StringBuilder)localObject).toString());
          }
        }
        return new StatusLine((Protocol)localObject, j, str);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unexpected status line: ");
        localStringBuilder.append(paramString);
        throw new ProtocolException(localStringBuilder.toString());
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unexpected status line: ");
    localStringBuilder.append(paramString);
    throw new ProtocolException(localStringBuilder.toString());
    label318:
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unexpected status line: ");
    localStringBuilder.append(paramString);
    throw new ProtocolException(localStringBuilder.toString());
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str;
    if (protocol == Protocol.HTTP_1_0) {
      str = "HTTP/1.0";
    } else {
      str = "HTTP/1.1";
    }
    localStringBuilder.append(str);
    localStringBuilder.append(' ');
    localStringBuilder.append(code);
    if (message != null)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(message);
    }
    return localStringBuilder.toString();
  }
}
