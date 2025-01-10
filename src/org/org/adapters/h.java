package org.org.adapters;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

class h
{
  public final int b;
  public final int c;
  public final byte[] d;
  
  private h(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    c = paramInt1;
    b = paramInt2;
    d = paramArrayOfByte;
  }
  
  /* Error */
  private Object a(ByteOrder paramByteOrder)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 16
    //   3: aconst_null
    //   4: astore 15
    //   6: aload_0
    //   7: getfield 22	org/org/adapters/h:d	[B
    //   10: astore 17
    //   12: new 34	org/org/adapters/SmbRandomAccessFile
    //   15: dup
    //   16: aload 17
    //   18: invokespecial 37	org/org/adapters/SmbRandomAccessFile:<init>	([B)V
    //   21: astore 17
    //   23: aload 17
    //   25: astore 16
    //   27: aload 16
    //   29: astore 15
    //   31: aload 17
    //   33: aload_1
    //   34: invokevirtual 41	org/org/adapters/SmbRandomAccessFile:write	(Ljava/nio/ByteOrder;)V
    //   37: aload 16
    //   39: astore 15
    //   41: aload_0
    //   42: getfield 18	org/org/adapters/h:c	I
    //   45: istore 6
    //   47: iconst_0
    //   48: istore 7
    //   50: iload 6
    //   52: lookupswitch	default:+108->160, 1:+1075->1127, 2:+807->859, 3:+729->781, 4:+651->703, 5:+543->595, 6:+1075->1127, 7:+807->859, 8:+465->517, 9:+387->439, 10:+269->321, 11:+190->242, 12:+114->166
    //   160: goto +3 -> 163
    //   163: goto +1117 -> 1280
    //   166: aload 16
    //   168: astore 15
    //   170: aload_0
    //   171: getfield 20	org/org/adapters/h:b	I
    //   174: newarray double
    //   176: astore_1
    //   177: iconst_0
    //   178: istore 6
    //   180: aload 16
    //   182: astore 15
    //   184: aload_0
    //   185: getfield 20	org/org/adapters/h:b	I
    //   188: istore 7
    //   190: iload 6
    //   192: iload 7
    //   194: if_icmpge +27 -> 221
    //   197: aload 16
    //   199: astore 15
    //   201: aload 17
    //   203: invokevirtual 45	org/org/adapters/SmbRandomAccessFile:readDouble	()D
    //   206: dstore_3
    //   207: aload_1
    //   208: iload 6
    //   210: dload_3
    //   211: dastore
    //   212: iload 6
    //   214: iconst_1
    //   215: iadd
    //   216: istore 6
    //   218: goto -38 -> 180
    //   221: aload 17
    //   223: invokevirtual 50	java/io/InputStream:close	()V
    //   226: aload_1
    //   227: areturn
    //   228: astore 15
    //   230: ldc 52
    //   232: ldc 54
    //   234: aload 15
    //   236: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   239: pop
    //   240: aload_1
    //   241: areturn
    //   242: aload 16
    //   244: astore 15
    //   246: aload_0
    //   247: getfield 20	org/org/adapters/h:b	I
    //   250: newarray double
    //   252: astore_1
    //   253: iconst_0
    //   254: istore 6
    //   256: aload 16
    //   258: astore 15
    //   260: aload_0
    //   261: getfield 20	org/org/adapters/h:b	I
    //   264: istore 7
    //   266: iload 6
    //   268: iload 7
    //   270: if_icmpge +30 -> 300
    //   273: aload 16
    //   275: astore 15
    //   277: aload 17
    //   279: invokevirtual 64	org/org/adapters/SmbRandomAccessFile:readFloat	()F
    //   282: fstore 5
    //   284: aload_1
    //   285: iload 6
    //   287: fload 5
    //   289: f2d
    //   290: dastore
    //   291: iload 6
    //   293: iconst_1
    //   294: iadd
    //   295: istore 6
    //   297: goto -41 -> 256
    //   300: aload 17
    //   302: invokevirtual 50	java/io/InputStream:close	()V
    //   305: aload_1
    //   306: areturn
    //   307: astore 15
    //   309: ldc 52
    //   311: ldc 54
    //   313: aload 15
    //   315: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   318: pop
    //   319: aload_1
    //   320: areturn
    //   321: aload 16
    //   323: astore 15
    //   325: aload_0
    //   326: getfield 20	org/org/adapters/h:b	I
    //   329: anewarray 66	org/org/adapters/Item
    //   332: astore_1
    //   333: iconst_0
    //   334: istore 6
    //   336: aload 16
    //   338: astore 15
    //   340: aload_0
    //   341: getfield 20	org/org/adapters/h:b	I
    //   344: istore 7
    //   346: iload 6
    //   348: iload 7
    //   350: if_icmpge +68 -> 418
    //   353: aload 16
    //   355: astore 15
    //   357: aload 17
    //   359: invokevirtual 70	org/org/adapters/SmbRandomAccessFile:readInt	()I
    //   362: istore 7
    //   364: iload 7
    //   366: i2l
    //   367: lstore 11
    //   369: aload 16
    //   371: astore 15
    //   373: aload 17
    //   375: invokevirtual 70	org/org/adapters/SmbRandomAccessFile:readInt	()I
    //   378: istore 7
    //   380: iload 7
    //   382: i2l
    //   383: lstore 13
    //   385: aload 16
    //   387: astore 15
    //   389: new 66	org/org/adapters/Item
    //   392: dup
    //   393: lload 11
    //   395: lload 13
    //   397: aconst_null
    //   398: invokespecial 73	org/org/adapters/Item:<init>	(JJLorg/org/adapters/IconProvider;)V
    //   401: astore 18
    //   403: aload_1
    //   404: iload 6
    //   406: aload 18
    //   408: aastore
    //   409: iload 6
    //   411: iconst_1
    //   412: iadd
    //   413: istore 6
    //   415: goto -79 -> 336
    //   418: aload 17
    //   420: invokevirtual 50	java/io/InputStream:close	()V
    //   423: aload_1
    //   424: areturn
    //   425: astore 15
    //   427: ldc 52
    //   429: ldc 54
    //   431: aload 15
    //   433: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   436: pop
    //   437: aload_1
    //   438: areturn
    //   439: aload 16
    //   441: astore 15
    //   443: aload_0
    //   444: getfield 20	org/org/adapters/h:b	I
    //   447: newarray int
    //   449: astore_1
    //   450: iconst_0
    //   451: istore 6
    //   453: aload 16
    //   455: astore 15
    //   457: aload_0
    //   458: getfield 20	org/org/adapters/h:b	I
    //   461: istore 7
    //   463: iload 6
    //   465: iload 7
    //   467: if_icmpge +29 -> 496
    //   470: aload 16
    //   472: astore 15
    //   474: aload 17
    //   476: invokevirtual 70	org/org/adapters/SmbRandomAccessFile:readInt	()I
    //   479: istore 7
    //   481: aload_1
    //   482: iload 6
    //   484: iload 7
    //   486: iastore
    //   487: iload 6
    //   489: iconst_1
    //   490: iadd
    //   491: istore 6
    //   493: goto -40 -> 453
    //   496: aload 17
    //   498: invokevirtual 50	java/io/InputStream:close	()V
    //   501: aload_1
    //   502: areturn
    //   503: astore 15
    //   505: ldc 52
    //   507: ldc 54
    //   509: aload 15
    //   511: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   514: pop
    //   515: aload_1
    //   516: areturn
    //   517: aload 16
    //   519: astore 15
    //   521: aload_0
    //   522: getfield 20	org/org/adapters/h:b	I
    //   525: newarray int
    //   527: astore_1
    //   528: iconst_0
    //   529: istore 6
    //   531: aload 16
    //   533: astore 15
    //   535: aload_0
    //   536: getfield 20	org/org/adapters/h:b	I
    //   539: istore 7
    //   541: iload 6
    //   543: iload 7
    //   545: if_icmpge +29 -> 574
    //   548: aload 16
    //   550: astore 15
    //   552: aload 17
    //   554: invokevirtual 77	org/org/adapters/SmbRandomAccessFile:readShort	()S
    //   557: istore 7
    //   559: aload_1
    //   560: iload 6
    //   562: iload 7
    //   564: iastore
    //   565: iload 6
    //   567: iconst_1
    //   568: iadd
    //   569: istore 6
    //   571: goto -40 -> 531
    //   574: aload 17
    //   576: invokevirtual 50	java/io/InputStream:close	()V
    //   579: aload_1
    //   580: areturn
    //   581: astore 15
    //   583: ldc 52
    //   585: ldc 54
    //   587: aload 15
    //   589: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   592: pop
    //   593: aload_1
    //   594: areturn
    //   595: aload 16
    //   597: astore 15
    //   599: aload_0
    //   600: getfield 20	org/org/adapters/h:b	I
    //   603: anewarray 66	org/org/adapters/Item
    //   606: astore_1
    //   607: iconst_0
    //   608: istore 6
    //   610: aload 16
    //   612: astore 15
    //   614: aload_0
    //   615: getfield 20	org/org/adapters/h:b	I
    //   618: istore 7
    //   620: iload 6
    //   622: iload 7
    //   624: if_icmpge +58 -> 682
    //   627: aload 16
    //   629: astore 15
    //   631: aload 17
    //   633: invokevirtual 81	org/org/adapters/SmbRandomAccessFile:length	()J
    //   636: lstore 11
    //   638: aload 16
    //   640: astore 15
    //   642: aload 17
    //   644: invokevirtual 81	org/org/adapters/SmbRandomAccessFile:length	()J
    //   647: lstore 13
    //   649: aload 16
    //   651: astore 15
    //   653: new 66	org/org/adapters/Item
    //   656: dup
    //   657: lload 11
    //   659: lload 13
    //   661: aconst_null
    //   662: invokespecial 73	org/org/adapters/Item:<init>	(JJLorg/org/adapters/IconProvider;)V
    //   665: astore 18
    //   667: aload_1
    //   668: iload 6
    //   670: aload 18
    //   672: aastore
    //   673: iload 6
    //   675: iconst_1
    //   676: iadd
    //   677: istore 6
    //   679: goto -69 -> 610
    //   682: aload 17
    //   684: invokevirtual 50	java/io/InputStream:close	()V
    //   687: aload_1
    //   688: areturn
    //   689: astore 15
    //   691: ldc 52
    //   693: ldc 54
    //   695: aload 15
    //   697: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   700: pop
    //   701: aload_1
    //   702: areturn
    //   703: aload 16
    //   705: astore 15
    //   707: aload_0
    //   708: getfield 20	org/org/adapters/h:b	I
    //   711: newarray long
    //   713: astore_1
    //   714: iconst_0
    //   715: istore 6
    //   717: aload 16
    //   719: astore 15
    //   721: aload_0
    //   722: getfield 20	org/org/adapters/h:b	I
    //   725: istore 7
    //   727: iload 6
    //   729: iload 7
    //   731: if_icmpge +29 -> 760
    //   734: aload 16
    //   736: astore 15
    //   738: aload 17
    //   740: invokevirtual 81	org/org/adapters/SmbRandomAccessFile:length	()J
    //   743: lstore 11
    //   745: aload_1
    //   746: iload 6
    //   748: lload 11
    //   750: lastore
    //   751: iload 6
    //   753: iconst_1
    //   754: iadd
    //   755: istore 6
    //   757: goto -40 -> 717
    //   760: aload 17
    //   762: invokevirtual 50	java/io/InputStream:close	()V
    //   765: aload_1
    //   766: areturn
    //   767: astore 15
    //   769: ldc 52
    //   771: ldc 54
    //   773: aload 15
    //   775: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   778: pop
    //   779: aload_1
    //   780: areturn
    //   781: aload 16
    //   783: astore 15
    //   785: aload_0
    //   786: getfield 20	org/org/adapters/h:b	I
    //   789: newarray int
    //   791: astore_1
    //   792: iconst_0
    //   793: istore 6
    //   795: aload 16
    //   797: astore 15
    //   799: aload_0
    //   800: getfield 20	org/org/adapters/h:b	I
    //   803: istore 7
    //   805: iload 6
    //   807: iload 7
    //   809: if_icmpge +29 -> 838
    //   812: aload 16
    //   814: astore 15
    //   816: aload 17
    //   818: invokevirtual 84	org/org/adapters/SmbRandomAccessFile:readUnsignedShort	()I
    //   821: istore 7
    //   823: aload_1
    //   824: iload 6
    //   826: iload 7
    //   828: iastore
    //   829: iload 6
    //   831: iconst_1
    //   832: iadd
    //   833: istore 6
    //   835: goto -40 -> 795
    //   838: aload 17
    //   840: invokevirtual 50	java/io/InputStream:close	()V
    //   843: aload_1
    //   844: areturn
    //   845: astore 15
    //   847: ldc 52
    //   849: ldc 54
    //   851: aload 15
    //   853: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   856: pop
    //   857: aload_1
    //   858: areturn
    //   859: iconst_0
    //   860: istore 8
    //   862: aload 16
    //   864: astore 15
    //   866: aload_0
    //   867: getfield 20	org/org/adapters/h:b	I
    //   870: istore 9
    //   872: aload 16
    //   874: astore 15
    //   876: invokestatic 88	org/org/adapters/ClassWriter:get	()[B
    //   879: astore_1
    //   880: aload 16
    //   882: astore 15
    //   884: aload_1
    //   885: arraylength
    //   886: istore 10
    //   888: iload 8
    //   890: istore 6
    //   892: iload 9
    //   894: iload 10
    //   896: if_icmplt +107 -> 1003
    //   899: iconst_1
    //   900: istore 9
    //   902: iload 7
    //   904: istore 6
    //   906: aload 16
    //   908: astore 15
    //   910: invokestatic 88	org/org/adapters/ClassWriter:get	()[B
    //   913: astore_1
    //   914: aload 16
    //   916: astore 15
    //   918: aload_1
    //   919: arraylength
    //   920: istore 10
    //   922: iload 9
    //   924: istore 7
    //   926: iload 6
    //   928: iload 10
    //   930: if_icmpge +48 -> 978
    //   933: aload 16
    //   935: astore 15
    //   937: aload_0
    //   938: getfield 22	org/org/adapters/h:d	[B
    //   941: iload 6
    //   943: baload
    //   944: istore 7
    //   946: aload 16
    //   948: astore 15
    //   950: invokestatic 88	org/org/adapters/ClassWriter:get	()[B
    //   953: astore_1
    //   954: iload 7
    //   956: aload_1
    //   957: iload 6
    //   959: baload
    //   960: if_icmpeq +9 -> 969
    //   963: iconst_0
    //   964: istore 7
    //   966: goto +12 -> 978
    //   969: iload 6
    //   971: iconst_1
    //   972: iadd
    //   973: istore 6
    //   975: goto -69 -> 906
    //   978: iload 8
    //   980: istore 6
    //   982: iload 7
    //   984: ifeq +19 -> 1003
    //   987: aload 16
    //   989: astore 15
    //   991: invokestatic 88	org/org/adapters/ClassWriter:get	()[B
    //   994: astore_1
    //   995: aload 16
    //   997: astore 15
    //   999: aload_1
    //   1000: arraylength
    //   1001: istore 6
    //   1003: aload 16
    //   1005: astore 15
    //   1007: new 90	java/lang/StringBuilder
    //   1010: dup
    //   1011: invokespecial 91	java/lang/StringBuilder:<init>	()V
    //   1014: astore_1
    //   1015: aload 16
    //   1017: astore 15
    //   1019: aload_0
    //   1020: getfield 20	org/org/adapters/h:b	I
    //   1023: istore 7
    //   1025: iload 6
    //   1027: iload 7
    //   1029: if_icmpge +68 -> 1097
    //   1032: aload 16
    //   1034: astore 15
    //   1036: aload_0
    //   1037: getfield 22	org/org/adapters/h:d	[B
    //   1040: iload 6
    //   1042: baload
    //   1043: istore 7
    //   1045: iload 7
    //   1047: ifne +6 -> 1053
    //   1050: goto +47 -> 1097
    //   1053: iload 7
    //   1055: bipush 32
    //   1057: if_icmplt +20 -> 1077
    //   1060: iload 7
    //   1062: i2c
    //   1063: istore_2
    //   1064: aload 16
    //   1066: astore 15
    //   1068: aload_1
    //   1069: iload_2
    //   1070: invokevirtual 95	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   1073: pop
    //   1074: goto +14 -> 1088
    //   1077: aload 16
    //   1079: astore 15
    //   1081: aload_1
    //   1082: bipush 63
    //   1084: invokevirtual 95	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   1087: pop
    //   1088: iload 6
    //   1090: iconst_1
    //   1091: iadd
    //   1092: istore 6
    //   1094: goto -79 -> 1015
    //   1097: aload 16
    //   1099: astore 15
    //   1101: aload_1
    //   1102: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1105: astore_1
    //   1106: aload 17
    //   1108: invokevirtual 50	java/io/InputStream:close	()V
    //   1111: aload_1
    //   1112: areturn
    //   1113: astore 15
    //   1115: ldc 52
    //   1117: ldc 54
    //   1119: aload 15
    //   1121: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1124: pop
    //   1125: aload_1
    //   1126: areturn
    //   1127: aload 16
    //   1129: astore 15
    //   1131: aload_0
    //   1132: getfield 22	org/org/adapters/h:d	[B
    //   1135: arraylength
    //   1136: istore 6
    //   1138: iload 6
    //   1140: iconst_1
    //   1141: if_icmpne +97 -> 1238
    //   1144: aload 16
    //   1146: astore 15
    //   1148: aload_0
    //   1149: getfield 22	org/org/adapters/h:d	[B
    //   1152: iconst_0
    //   1153: baload
    //   1154: istore 6
    //   1156: iload 6
    //   1158: iflt +80 -> 1238
    //   1161: aload 16
    //   1163: astore 15
    //   1165: aload_0
    //   1166: getfield 22	org/org/adapters/h:d	[B
    //   1169: iconst_0
    //   1170: baload
    //   1171: istore 6
    //   1173: iload 6
    //   1175: iconst_1
    //   1176: if_icmpgt +62 -> 1238
    //   1179: aload 16
    //   1181: astore 15
    //   1183: aload_0
    //   1184: getfield 22	org/org/adapters/h:d	[B
    //   1187: iconst_0
    //   1188: baload
    //   1189: istore 6
    //   1191: iload 6
    //   1193: bipush 48
    //   1195: iadd
    //   1196: i2c
    //   1197: istore_2
    //   1198: aload 16
    //   1200: astore 15
    //   1202: new 101	java/lang/String
    //   1205: dup
    //   1206: iconst_1
    //   1207: newarray char
    //   1209: dup
    //   1210: iconst_0
    //   1211: iload_2
    //   1212: castore
    //   1213: invokespecial 104	java/lang/String:<init>	([C)V
    //   1216: astore_1
    //   1217: aload 17
    //   1219: invokevirtual 50	java/io/InputStream:close	()V
    //   1222: aload_1
    //   1223: areturn
    //   1224: astore 15
    //   1226: ldc 52
    //   1228: ldc 54
    //   1230: aload 15
    //   1232: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1235: pop
    //   1236: aload_1
    //   1237: areturn
    //   1238: aload_0
    //   1239: getfield 22	org/org/adapters/h:d	[B
    //   1242: astore_1
    //   1243: aload 16
    //   1245: astore 15
    //   1247: new 101	java/lang/String
    //   1250: dup
    //   1251: aload_1
    //   1252: invokestatic 108	org/org/adapters/ClassWriter:visitTypeAnnotation	()Ljava/nio/charset/Charset;
    //   1255: invokespecial 111	java/lang/String:<init>	([BLjava/nio/charset/Charset;)V
    //   1258: astore_1
    //   1259: aload 17
    //   1261: invokevirtual 50	java/io/InputStream:close	()V
    //   1264: aload_1
    //   1265: areturn
    //   1266: astore 15
    //   1268: ldc 52
    //   1270: ldc 54
    //   1272: aload 15
    //   1274: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1277: pop
    //   1278: aload_1
    //   1279: areturn
    //   1280: aload 17
    //   1282: invokevirtual 50	java/io/InputStream:close	()V
    //   1285: aconst_null
    //   1286: areturn
    //   1287: astore_1
    //   1288: ldc 52
    //   1290: ldc 54
    //   1292: aload_1
    //   1293: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1296: pop
    //   1297: aconst_null
    //   1298: areturn
    //   1299: astore_1
    //   1300: goto +8 -> 1308
    //   1303: astore_1
    //   1304: goto +45 -> 1349
    //   1307: astore_1
    //   1308: aload 16
    //   1310: astore 15
    //   1312: ldc 52
    //   1314: ldc 113
    //   1316: aload_1
    //   1317: invokestatic 116	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1320: pop
    //   1321: aload 16
    //   1323: ifnull +56 -> 1379
    //   1326: aload 16
    //   1328: invokevirtual 50	java/io/InputStream:close	()V
    //   1331: aconst_null
    //   1332: areturn
    //   1333: astore_1
    //   1334: ldc 52
    //   1336: ldc 54
    //   1338: aload_1
    //   1339: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1342: pop
    //   1343: aconst_null
    //   1344: areturn
    //   1345: astore_1
    //   1346: goto -42 -> 1304
    //   1349: aload 15
    //   1351: ifnull +23 -> 1374
    //   1354: aload 15
    //   1356: invokevirtual 50	java/io/InputStream:close	()V
    //   1359: goto +15 -> 1374
    //   1362: astore 15
    //   1364: ldc 52
    //   1366: ldc 54
    //   1368: aload 15
    //   1370: invokestatic 60	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1373: pop
    //   1374: goto +3 -> 1377
    //   1377: aload_1
    //   1378: athrow
    //   1379: aconst_null
    //   1380: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1381	0	this	h
    //   0	1381	1	paramByteOrder	ByteOrder
    //   1063	149	2	c1	char
    //   206	5	3	d1	double
    //   282	6	5	f	float
    //   45	1151	6	i	int
    //   48	1013	7	j	int
    //   860	119	8	k	int
    //   870	53	9	m	int
    //   886	45	10	n	int
    //   367	382	11	l1	long
    //   383	277	13	l2	long
    //   4	196	15	localObject1	Object
    //   228	7	15	localIOException1	java.io.IOException
    //   244	32	15	localObject2	Object
    //   307	7	15	localIOException2	java.io.IOException
    //   323	65	15	localObject3	Object
    //   425	7	15	localIOException3	java.io.IOException
    //   441	32	15	localObject4	Object
    //   503	7	15	localIOException4	java.io.IOException
    //   519	32	15	localObject5	Object
    //   581	7	15	localIOException5	java.io.IOException
    //   597	55	15	localObject6	Object
    //   689	7	15	localIOException6	java.io.IOException
    //   705	32	15	localObject7	Object
    //   767	7	15	localIOException7	java.io.IOException
    //   783	32	15	localObject8	Object
    //   845	7	15	localIOException8	java.io.IOException
    //   864	236	15	localObject9	Object
    //   1113	7	15	localIOException9	java.io.IOException
    //   1129	72	15	localObject10	Object
    //   1224	7	15	localIOException10	java.io.IOException
    //   1245	1	15	localObject11	Object
    //   1266	7	15	localIOException11	java.io.IOException
    //   1310	45	15	localObject12	Object
    //   1362	7	15	localIOException12	java.io.IOException
    //   1	1326	16	localObject13	Object
    //   10	1271	17	localObject14	Object
    //   401	270	18	localItem	Item
    // Exception table:
    //   from	to	target	type
    //   221	226	228	java/io/IOException
    //   300	305	307	java/io/IOException
    //   418	423	425	java/io/IOException
    //   496	501	503	java/io/IOException
    //   574	579	581	java/io/IOException
    //   682	687	689	java/io/IOException
    //   760	765	767	java/io/IOException
    //   838	843	845	java/io/IOException
    //   1106	1111	1113	java/io/IOException
    //   1217	1222	1224	java/io/IOException
    //   1259	1264	1266	java/io/IOException
    //   1280	1285	1287	java/io/IOException
    //   31	37	1299	java/io/IOException
    //   201	207	1299	java/io/IOException
    //   277	284	1299	java/io/IOException
    //   357	364	1299	java/io/IOException
    //   373	380	1299	java/io/IOException
    //   389	403	1299	java/io/IOException
    //   474	481	1299	java/io/IOException
    //   552	559	1299	java/io/IOException
    //   631	638	1299	java/io/IOException
    //   642	649	1299	java/io/IOException
    //   653	667	1299	java/io/IOException
    //   738	745	1299	java/io/IOException
    //   816	823	1299	java/io/IOException
    //   876	880	1299	java/io/IOException
    //   910	914	1299	java/io/IOException
    //   950	954	1299	java/io/IOException
    //   991	995	1299	java/io/IOException
    //   1007	1015	1299	java/io/IOException
    //   1068	1074	1299	java/io/IOException
    //   1081	1088	1299	java/io/IOException
    //   1101	1106	1299	java/io/IOException
    //   1202	1217	1299	java/io/IOException
    //   1247	1259	1299	java/io/IOException
    //   12	23	1303	java/lang/Throwable
    //   12	23	1307	java/io/IOException
    //   1326	1331	1333	java/io/IOException
    //   31	37	1345	java/lang/Throwable
    //   41	47	1345	java/lang/Throwable
    //   170	177	1345	java/lang/Throwable
    //   184	190	1345	java/lang/Throwable
    //   201	207	1345	java/lang/Throwable
    //   246	253	1345	java/lang/Throwable
    //   260	266	1345	java/lang/Throwable
    //   277	284	1345	java/lang/Throwable
    //   325	333	1345	java/lang/Throwable
    //   340	346	1345	java/lang/Throwable
    //   357	364	1345	java/lang/Throwable
    //   373	380	1345	java/lang/Throwable
    //   389	403	1345	java/lang/Throwable
    //   443	450	1345	java/lang/Throwable
    //   457	463	1345	java/lang/Throwable
    //   474	481	1345	java/lang/Throwable
    //   521	528	1345	java/lang/Throwable
    //   535	541	1345	java/lang/Throwable
    //   552	559	1345	java/lang/Throwable
    //   599	607	1345	java/lang/Throwable
    //   614	620	1345	java/lang/Throwable
    //   631	638	1345	java/lang/Throwable
    //   642	649	1345	java/lang/Throwable
    //   653	667	1345	java/lang/Throwable
    //   707	714	1345	java/lang/Throwable
    //   721	727	1345	java/lang/Throwable
    //   738	745	1345	java/lang/Throwable
    //   785	792	1345	java/lang/Throwable
    //   799	805	1345	java/lang/Throwable
    //   816	823	1345	java/lang/Throwable
    //   866	872	1345	java/lang/Throwable
    //   876	880	1345	java/lang/Throwable
    //   884	888	1345	java/lang/Throwable
    //   910	914	1345	java/lang/Throwable
    //   918	922	1345	java/lang/Throwable
    //   937	946	1345	java/lang/Throwable
    //   950	954	1345	java/lang/Throwable
    //   991	995	1345	java/lang/Throwable
    //   999	1003	1345	java/lang/Throwable
    //   1007	1015	1345	java/lang/Throwable
    //   1019	1025	1345	java/lang/Throwable
    //   1036	1045	1345	java/lang/Throwable
    //   1068	1074	1345	java/lang/Throwable
    //   1081	1088	1345	java/lang/Throwable
    //   1101	1106	1345	java/lang/Throwable
    //   1131	1138	1345	java/lang/Throwable
    //   1148	1156	1345	java/lang/Throwable
    //   1165	1173	1345	java/lang/Throwable
    //   1183	1191	1345	java/lang/Throwable
    //   1202	1217	1345	java/lang/Throwable
    //   1247	1259	1345	java/lang/Throwable
    //   1312	1321	1345	java/lang/Throwable
    //   1354	1359	1362	java/io/IOException
  }
  
  public static h a(int paramInt, ByteOrder paramByteOrder)
  {
    return a(new int[] { paramInt }, paramByteOrder);
  }
  
  public static h a(long paramLong, ByteOrder paramByteOrder)
  {
    return read(new long[] { paramLong }, paramByteOrder);
  }
  
  public static h a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append('\000');
    paramString = localStringBuilder.toString().getBytes(ClassWriter.visitTypeAnnotation());
    return new h(2, paramString.length, paramString);
  }
  
  public static h a(Item paramItem, ByteOrder paramByteOrder)
  {
    return a(new Item[] { paramItem }, paramByteOrder);
  }
  
  public static h a(int[] paramArrayOfInt, ByteOrder paramByteOrder)
  {
    ByteBuffer localByteBuffer = ByteBuffer.wrap(new byte[ClassWriter.c[3] * paramArrayOfInt.length]);
    localByteBuffer.order(paramByteOrder);
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      localByteBuffer.putShort((short)paramArrayOfInt[i]);
      i += 1;
    }
    return new h(3, paramArrayOfInt.length, localByteBuffer.array());
  }
  
  public static h a(Item[] paramArrayOfItem, ByteOrder paramByteOrder)
  {
    ByteBuffer localByteBuffer = ByteBuffer.wrap(new byte[ClassWriter.c[5] * paramArrayOfItem.length]);
    localByteBuffer.order(paramByteOrder);
    int j = paramArrayOfItem.length;
    int i = 0;
    while (i < j)
    {
      paramByteOrder = paramArrayOfItem[i];
      localByteBuffer.putInt((int)a);
      localByteBuffer.putInt((int)b);
      i += 1;
    }
    return new h(5, paramArrayOfItem.length, localByteBuffer.array());
  }
  
  public static h read(long[] paramArrayOfLong, ByteOrder paramByteOrder)
  {
    ByteBuffer localByteBuffer = ByteBuffer.wrap(new byte[ClassWriter.c[4] * paramArrayOfLong.length]);
    localByteBuffer.order(paramByteOrder);
    int j = paramArrayOfLong.length;
    int i = 0;
    while (i < j)
    {
      localByteBuffer.putInt((int)paramArrayOfLong[i]);
      i += 1;
    }
    return new h(4, paramArrayOfLong.length, localByteBuffer.array());
  }
  
  public double add(ByteOrder paramByteOrder)
  {
    paramByteOrder = a(paramByteOrder);
    if (paramByteOrder != null)
    {
      if ((paramByteOrder instanceof String)) {
        return Double.parseDouble((String)paramByteOrder);
      }
      if ((paramByteOrder instanceof long[]))
      {
        paramByteOrder = (long[])paramByteOrder;
        if (paramByteOrder.length == 1) {
          return paramByteOrder[0];
        }
        throw new NumberFormatException("There are more than one component");
      }
      if ((paramByteOrder instanceof int[]))
      {
        paramByteOrder = (int[])paramByteOrder;
        if (paramByteOrder.length == 1) {
          return paramByteOrder[0];
        }
        throw new NumberFormatException("There are more than one component");
      }
      if ((paramByteOrder instanceof double[]))
      {
        paramByteOrder = (double[])paramByteOrder;
        if (paramByteOrder.length == 1) {
          return paramByteOrder[0];
        }
        throw new NumberFormatException("There are more than one component");
      }
      if ((paramByteOrder instanceof Item[]))
      {
        paramByteOrder = (Item[])paramByteOrder;
        if (paramByteOrder.length == 1) {
          return paramByteOrder[0].a();
        }
        throw new NumberFormatException("There are more than one component");
      }
      throw new NumberFormatException("Couldn't find a double value");
    }
    throw new NumberFormatException("NULL can't be converted to a double value");
  }
  
  public int b(ByteOrder paramByteOrder)
  {
    paramByteOrder = a(paramByteOrder);
    if (paramByteOrder != null)
    {
      if ((paramByteOrder instanceof String)) {
        return Integer.parseInt((String)paramByteOrder);
      }
      if ((paramByteOrder instanceof long[]))
      {
        paramByteOrder = (long[])paramByteOrder;
        if (paramByteOrder.length == 1) {
          return (int)paramByteOrder[0];
        }
        throw new NumberFormatException("There are more than one component");
      }
      if ((paramByteOrder instanceof int[]))
      {
        paramByteOrder = (int[])paramByteOrder;
        if (paramByteOrder.length == 1) {
          return paramByteOrder[0];
        }
        throw new NumberFormatException("There are more than one component");
      }
      throw new NumberFormatException("Couldn't find a integer value");
    }
    throw new NumberFormatException("NULL can't be converted to a integer value");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("(");
    localStringBuilder.append(ClassWriter.s[c]);
    localStringBuilder.append(", data length:");
    localStringBuilder.append(d.length);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public String write(ByteOrder paramByteOrder)
  {
    Object localObject = a(paramByteOrder);
    if (localObject == null) {
      return null;
    }
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    paramByteOrder = new StringBuilder();
    int i;
    if ((localObject instanceof long[]))
    {
      localObject = (long[])localObject;
      i = 0;
      while (i < localObject.length)
      {
        paramByteOrder.append(localObject[i]);
        if (i + 1 != localObject.length) {
          paramByteOrder.append(",");
        }
        i += 1;
      }
      return paramByteOrder.toString();
    }
    if ((localObject instanceof int[]))
    {
      localObject = (int[])localObject;
      i = 0;
      while (i < localObject.length)
      {
        paramByteOrder.append(localObject[i]);
        if (i + 1 != localObject.length) {
          paramByteOrder.append(",");
        }
        i += 1;
      }
      return paramByteOrder.toString();
    }
    if ((localObject instanceof double[]))
    {
      localObject = (double[])localObject;
      i = 0;
      while (i < localObject.length)
      {
        paramByteOrder.append(localObject[i]);
        if (i + 1 != localObject.length) {
          paramByteOrder.append(",");
        }
        i += 1;
      }
      return paramByteOrder.toString();
    }
    if ((localObject instanceof Item[]))
    {
      localObject = (Item[])localObject;
      i = 0;
      while (i < localObject.length)
      {
        paramByteOrder.append(a);
        paramByteOrder.append('/');
        paramByteOrder.append(b);
        if (i + 1 != localObject.length) {
          paramByteOrder.append(",");
        }
        i += 1;
      }
      return paramByteOrder.toString();
    }
    return null;
  }
}
