package okhttp;

import java.util.regex.Pattern;

public final class MediaType
{
  static
  {
    Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
  }
  
  public String toString()
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
}
