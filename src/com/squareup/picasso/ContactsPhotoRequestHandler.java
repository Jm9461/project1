package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import java.io.InputStream;
import okio.Okio;

class ContactsPhotoRequestHandler
  extends RequestHandler
{
  private static final UriMatcher matcher = new UriMatcher(-1);
  private final Context context;
  
  static
  {
    matcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
    matcher.addURI("com.android.contacts", "contacts/lookup/*", 1);
    matcher.addURI("com.android.contacts", "contacts/#/photo", 2);
    matcher.addURI("com.android.contacts", "contacts/#", 3);
    matcher.addURI("com.android.contacts", "display_photo/#", 4);
  }
  
  ContactsPhotoRequestHandler(Context paramContext)
  {
    context = paramContext;
  }
  
  private InputStream getInputStream(Request paramRequest)
  {
    ContentResolver localContentResolver = context.getContentResolver();
    Uri localUri = uri;
    int i = matcher.match(localUri);
    if (i != 1)
    {
      if (i != 2)
      {
        paramRequest = localUri;
        if (i == 3) {
          break label102;
        }
        if (i != 4)
        {
          paramRequest = new StringBuilder();
          paramRequest.append("Invalid uri: ");
          paramRequest.append(localUri);
          throw new IllegalStateException(paramRequest.toString());
        }
      }
      return localContentResolver.openInputStream(localUri);
    }
    else
    {
      localUri = ContactsContract.Contacts.lookupContact(localContentResolver, localUri);
      paramRequest = localUri;
      if (localUri == null) {
        return null;
      }
    }
    label102:
    return ContactsContract.Contacts.openContactPhotoInputStream(localContentResolver, paramRequest, true);
  }
  
  public boolean canHandleRequest(Request paramRequest)
  {
    Uri localUri = uri;
    return ("content".equals(localUri.getScheme())) && (ContactsContract.Contacts.CONTENT_URI.getHost().equals(localUri.getHost())) && (matcher.match(uri) != -1);
  }
  
  public RequestHandler.Result load(Request paramRequest, int paramInt)
  {
    paramRequest = getInputStream(paramRequest);
    if (paramRequest == null) {
      return null;
    }
    return new RequestHandler.Result(Okio.source(paramRequest), Picasso.LoadedFrom.DISK);
  }
}
