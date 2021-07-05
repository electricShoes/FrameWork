package cspi.ezsmart.common.util.security;


public abstract class KisaSeed256
{
  private static final String _ENC_LANG = "UTF-8";
  private static final String _KEY = "aHR0cDovL3d3dy5hdC1zeXMuY28ua3I=";
  private static int[] SS0 = new int[] { 
      696885672, 92635524, 382128852, 331600848, 340021332, 487395612, 747413676, 621093156, 
      491606364, 54739776, 403181592, 504238620, 289493328, 1020063996, 181060296, 591618912, 
      671621160, 71581764, 536879136, 495817116, 549511392, 583197408, 147374280, 386339604, 
      629514660, 261063564, 50529024, 994800504, 999011256, 318968592, 314757840, 785310444, 
      809529456, 210534540, 1057960764, 680042664, 839004720, 500027868, 919007988, 876900468, 
      751624428, 361075092, 185271048, 390550356, 474763356, 457921368, 1032696252, 16843008, 
      604250148, 470552604, 860058480, 411603096, 268439568, 214745292, 851636976, 432656856, 
      738992172, 667411428, 843215472, 58950528, 462132120, 297914832, 109478532, 164217288, 
      541089888, 272650320, 595829664, 734782440, 218956044, 914797236, 512660124, 256852812, 
      931640244, 441078360, 113689284, 944271480, 646357668, 302125584, 797942700, 365285844, 
      557932896, 63161280, 881111220, 21053760, 306336336, 1028485500, 227377548, 134742024, 
      521081628, 428446104, 420024600, 67371012, 323179344, 935850996, 566354400, 
      1036907004, 910586484, 789521196, 654779172, 813740208, 193692552, 235799052, 730571688, 
      578986656, 776888940, 327390096, 223166796, 692674920, 1011642492, 151585032, 168428040, 
      1066382268, 802153452, 868479984, 96846276, 126321540, 335810580, 1053750012, 608460900, 
      516870876, 772678188, 189481800, 436867608, 101057028, 553722144, 726360936, 642146916, 
      33686016, 902164980, 310547088, 176849544, 202113036, 864269232, 1045328508, 281071824, 
      977957496, 122110788, 377918100, 633725412, 637936164, 8421504, 764256684, 533713884, 
      562143648, 805318704, 923218740, 781099692, 906375732, 352653588, 570565152, 940060728, 
      885321972, 663200676, 88424772, 206323788, 25264512, 701096424, 75792516, 394761108, 
      889532724, 197903304, 248431308, 1007431740, 826372464, 285282576, 130532292, 160006536, 
      893743476, 1003222008, 449499864, 952692984, 344232084, 424235352, 42107520, 80003268, 
      1070593020, 155795784, 956903736, 658989924, 12632256, 265274316, 398971860, 948482232, 
      252642060, 244220556, 37896768, 587408160, 293704080, 743202924, 466342872, 612671652, 
      872689716, 834793968, 138952776, 46318272, 793731948, 1024274748, 755835180, 4210752, 
      1049539260, 1041117756, 1015853244, 29475264, 713728680, 982168248, 240009804, 356864340, 
      990589752, 483184860, 675831912, 1062171516, 478974108, 415813848, 172638792, 373707348, 
      927429492, 545300640, 768467436, 105267780, 897954228, 722150184, 625303908, 986379000, 
      600040416, 965325240, 830583216, 529503132, 508449372, 969535992, 650568420, 847426224, 
      822161712, 717939432, 760045932, 525292380, 616882404, 817950960, 231588300, 143163528, 
      369496596, 973746744, 407392344, 348442836, 574775904, 688464168, 117900036, 855847728, 
      684253416, 453710616, 84214020, 961114488, 276861072, 709517928, 705307176, 445289112 };

  
  private static int[] SS1 = new int[] { 
      943196208, -399980320, 741149985, -1540979038, -871379005, -601960750, -1338801229, -1204254544, 
      -1406169181, 1612726368, 1410680145, -1006123069, 1141130304, 1815039843, 1747667811, 1478183763, 
      -1073495101, 1612857954, 808649523, -1271560783, 673777953, -1608482656, -534592798, -1540913245, 
      -804011053, -1877900911, 269549841, 67503618, 471600144, -1136882512, 875955762, 1208699715, 
      -332410909, -2012706688, 1814842464, -1473738592, 337053459, -1006320448, 336987666, -197868304, 
      -1073560894, 1141196097, -534658591, -736704814, 1010765619, 1010634033, -1945203070, -1743222640, 
      673712160, 1276005954, -197736718, 1010699826, -1541044831, -130430479, 202181889, -601894957, 
      -669464368, 673909539, 1680229986, 2017086066, 606537507, 741281571, -265174543, 1882342002, 
      1073889858, -736836400, 1073824065, -1073692480, 1882407795, 1680295779, -1406366560, -2012509309, 
      -197670925, -1406300767, -2147450752, 471797523, -938816830, 741084192, -1473607006, 875824176, 
      -804076846, 134941443, -332476702, -399914527, 1545424209, -1810594672, 404228112, -130496272, 
      1410811731, -1406234974, 134744064, -1006254655, 269681427, -871510591, -2079947134, -1204188751, 
      -62926861, 2084392305, -1073626687, 808517937, -197802511, -2012575102, 1747602018, -1338932815, 
      -804142639, 538968096, -736639021, 131586, 539099682, 67372032, 1747470432, 1882276209, 
      67569411, -669266989, -1675784815, -1743156847, 1612792161, -1136750926, -467220766, 1478052177, 
      -602026543, 1343308113, -1877966704, -602092336, -1743091054, -1608285277, -1473541213, -804208432, 
      -2147384959, 202313475, 1141327683, 404359698, -534527005, -332608288, -1945268863, -1136685133, 
      -1810463086, 2017151859, 1545358416, -1608351070, -1608416863, 1612923747, 539165475, 1275940161, 
      -938948416, -1675719022, -1675850608, 943327794, 202116096, 741215778, -1204122958, 1814974050, 
      -1675653229, 1478117970, -265108750, -1877835118, -265042957, 1208568129, 2016954480, -871576384, 
      336921873, -130298893, 1882210416, 1949648241, 2084523891, 875889969, 269484048, 197379, 
      1680098400, 1814908257, -1006188862, 1949582448, -736770607, -1271626576, -399848734, 134809857, 
      1949714034, 404293905, -62992654, 1073758272, 269615634, -534724384, -1136816719, 67437825, 
      -130364686, 65793, -265240336, 673843746, 1545490002, -1473672799, 1410745938, 1073955651, 
      -2080012927, 336856080, -2012640895, -1743025261, -1338998608, -467286559, 1208502336, 2017020273, 
      -1810397293, -63124240, 471731730, -2147319166, 539033889, -1945334656, 404425491, 1545555795, 
      1949779827, 1410614352, -1338867022, 471665937, 606405921, 1276071747, 1141261890, 
      -332542495, 1477986384, 1343373906, -399782941, 2084458098, -669332782, -938882623, -63058447, 
      808452144, -1810528879, 1680164193, 1010568240, -1271494990, -467352352, -1204057165, 2084326512, 
      202247682, 1343242320, 943262001, 606471714, 808583730, -2080078720, 1747536225, -1877769325, 
      876021555, -467154973, 606340128, -1541110624, -938751037, 1343439699, 134875650, -2079881341, 
      -669398575, 1275874368, -2147253373, -1945137277, -871444798, 943393587, 1208633922, -1271429197 };

  
  private static int[] SS2 = new int[] { 
      -1582814839, -2122054267, -757852474, -741338173, 1347687492, 287055117, -1599329140, 556016901, 
      1364991309, 1128268611, 270014472, 303832590, 1364201793, -251904820, -1027077430, 1667244867, 
      539502600, 1078199364, 538976256, -1852039795, -522182464, -488627518, -1060632376, 320083719, 
      -1583078011, -2087972977, 50332419, 1937259339, -1279771765, 319820547, -758115646, -487838002, 
      1886400576, -2138305396, 859586319, -1599592312, 842019330, -774103603, -218876218, 1886663748, 
      -521392948, -1852566139, 50858763, 1398019911, 1348213836, 1398283083, -1313063539, 16777473, 
      539239428, 270277644, 1936732995, -1869080440, 269488128, -1060369204, -219139390, -774366775, 
      539765772, -471586873, 1919955522, -2088762493, -1818748021, -774893119, -2105276794, -1043854903, 
      1616912448, 1347424320, -1549786237, -471323701, 17566989, -1296812410, -1835262322, 1129058127, 
      -1280034937, 1381505610, -1027340602, 1886926920, -1566300538, 303043074, -1548996721, -774629947, 
      1633689921, -1010826301, -1330367356, 1094713665, 1380979266, 1903967565, -2121527923, 526344, 
      320610063, -1852302967, 286791945, 263172, 1397756739, -202098745, -505404991, 
      -235127347, 1920218694, 590098191, 589571847, -1330630528, -2088236149, 34344462, -1549259893, 
      -1566563710, 1651256910, -1819274365, 1095503181, 1634216265, 1887190092, 17303817, 34081290, 
      -1279508593, -471060529, -202361917, -1044118075, -2088499321, 269751300, -218349874, 1617175620, 
      -757326130, 573320718, 1128794955, 303569418, 33818118, 555753729, 1667771211, 1650730566, 
      33554946, -235653691, -1836051838, -2105013622, 789516, -1280298109, 1920745038, -791670592, 
      1920481866, 1128531783, -1835788666, -505141819, 572794374, -2139094912, -1582551667, -740548657, 
      -1583341183, 808464384, 859059975, -1565774194, 842282502, 286528773, 572531202, 808990728, 
      -252431164, -1549523065, 1094976837, 1078725708, -2122317439, -504878647, -2138831740, -1819011193, 
      825505029, -1010299957, -1026814258, 809253900, 1903178049, 286265601, -1010563129, -2121791095, 
      1903441221, -201835573, -757589302, -252167992, -1869343612, 1364728137, -2105539966, -1060895548, 
      -201572401, 1095240009, 825768201, 1667508039, -1061158720, -1010036785, -741075001, -1330104184, 
      51121935, -2104750450, 1111491138, 589308675, -1852829311, 1617701964, -740811829, -1599855484, 
      808727556, -235916863, 1078462536, -1027603774, 1668034383, 826031373, 556543245, 1077936192, 
      -1296286066, 842808846, -1329841012, -1044381247, -1566037366, -1296549238, 1112280654, 1364464965, 
      859323147, -790881076, 1617438792, 1937522511, -1868817268, -791144248, 1112017482, 1381242438, 
      1936996167, -1600118656, -504615475, 1111754310, -1313589883, 589835019, 1633953093, -218613046, 
      -471850045, -1313326711, -1313853055, -1818484849, 1381768782, -235390519, -488364346, -1297075582, 
      825241857, -488101174, 1634479437, 1398546255, -521919292, -252694336, -1043591731, -2138568568, 
      303306246, 842545674, 1347950664, -791407420, 1650467394, 556280073, 50595591, 858796803, 
      -521656120, 320346891, 17040645, 1903704393, -1869606784, 1650993738, 573057546, -1835525494 };

  
  private static int[] SS3 = new int[] { 
      137377848, -924784600, 220277805, -2036161498, -809251825, -825041890, -2085375949, -2001684424, 
      -1885098961, 1080057888, 1162957845, -943471609, 1145062404, 1331915823, 1264805931, 1263753243, 
      -1010581501, 1113743394, 53686323, -2051951563, 153167913, -2136956896, -1025318878, -2019318745, 
      -1009528813, -2121166831, 17895441, 100795398, 202382364, -1934574532, 103953462, 1262700555, 
      -807146449, -2004842488, 1281387564, -2002737112, 118690839, -993999868, 101848086, -990841804, 
      -1027424254, 1161905157, -1042161631, -959261674, 255015999, 221330493, -1904047090, -2003789800, 
      136325160, 1312967694, -957156298, 238173246, -2053004251, -906889159, 218172429, -808199137, 
      -925837288, 186853419, 1180853286, 1249015866, 119743527, 253963311, -1041108943, 1114796082, 
      1111638018, -992947180, 1094795265, -1061109760, 1131638835, 1197696039, -1935627220, -1954314229, 
      -940313545, -1918784467, -2139062272, 252910623, -893204470, 203435052, -1969051606, 70267956, 
      -1026371566, 184748043, -823989202, -907941847, 1297177629, -2070899692, 135272472, -923731912, 
      1196643351, -1901941714, 134219784, -977157115, 51580947, -842937331, -2038266874, -1984841671, 
      -806093761, 1299283005, -1044267007, 20000817, -973999051, -1971156982, 1247963178, -2119061455, 
      -1043214319, 2105376, -942418921, 33685506, 35790882, 67109892, 1214277672, 1097953329, 
      117638151, -875309029, -1919837155, -1986947047, 1096900641, -1900889026, -958208986, 1230067737, 
      -841884643, 1095847953, -2138009584, -858727396, -1970104294, -2086428637, -1952208853, -1060057072, 
      -2122219519, 251857935, 1195590663, 168957978, -1008476125, -857674708, -1920889843, -1884046273, 
      -2037214186, 1265858619, 1280334876, -2103271390, -2120114143, 1130586147, 52633635, 1296124941, 
      -926889976, -1902994402, -1936679908, 171063354, 201329676, 237120558, -1967998918, 1315073070, 
      -1886151649, 1246910490, -1024266190, -2104324078, -1007423437, 1229015049, 1215330360, -859780084, 
      85005333, -873203653, 1081110576, 1165063221, 1332968511, 87110709, 1052688, 50528259, 
      1147167780, 1298230317, -960314362, 1148220468, -976104427, -2068794316, -891099094, 151062537, 
      1181905974, 152115225, -822936514, 1077952512, 34738194, -1059004384, -1917731779, 83952645, 
      -890046406, 16842753, -1057951696, 170010666, 1314020382, -1985894359, 1179800598, 1128480771, 
      -2055109627, 68162580, -1987999735, -1953261541, -2135904208, -975051739, 1212172296, 1232173113, 
      -2020371433, -856622020, 236067870, -2105376766, 18948129, -1937732596, 185800731, 1330863135, 
      1198748727, 1146115092, -2102218702, 219225117, 86058021, 1329810447, 1178747910, 
      -840831955, 1213224984, 1112690706, -874256341, 1316125758, -892151782, -910047223, -839779267, 
      3158064, -2054056939, 1164010533, 204487740, -2035108810, -991894492, -1951156165, 1282440252, 
      235015182, 1079005200, 154220601, 102900774, 36843570, -2071952380, 1231120425, -2087481325, 
      120796215, -941366233, 69215268, -2069847004, -876361717, 1129533459, 167905290, -2021424121, 
      -908994535, 1279282188, -2088534013, -1887204337, -826094578, 187906107, 1245857802, -2018266057 };

  
  private static int[] KC = new int[] { 
      -1640531527, 1013904243, 2027808486, -239350324, -478700647, 
      -957401293, -1914802585, 465362127, 930724254, 1861448508, 
      -572070280, -1144140559, 2006686179, -281594938, -563189875, 
      -1126379749, 2042207799, -210551698, -421103395, -842206789, 
      -1684413577, 926140143, 1852280286, -590406724 };

  
  private static Boolean BIG = Boolean.valueOf(true);
  private static Boolean ENDIAN = BIG;
  
  private static int getB0(int A) {
    return 0xFF & A;
  }
  
  private static int getB1(int A) {
    return 0xFF & A >>> 8;
  }
  
  private static int getB2(int A) {
    return 0xFF & A >>> 16;
  }
  
  private static int getB3(int A) {
    return 0xFF & A >>> 24;
  }
  
  private static void endianChange(int[] dws) {
    dws[0] = dws[0] >>> 24 | dws[0] << 24 | dws[0] << 8 & 0xFF0000 | dws[0] >>> 8 & 0xFF00;
  }
  
  private static int endianChange(int dws) {
    return dws >>> 24 | dws << 24 | dws << 8 & 0xFF0000 | dws >>> 8 & 0xFF00;
  }

  
  private static void seedRound(int[] L0, int[] L1, int[] R0, int[] R1, int[] K) {
    long T00 = 0L, T11 = 0L;
    
    int T0 = R0[0] ^ K[0];
    int T1 = R1[0] ^ K[1];
    T1 ^= T0;
    T00 = (T0 < 0) ? ((T0 & Integer.MAX_VALUE) | 0xFFFFFFFF80000000L) : T0;
    T1 = SS0[getB0(T1)] ^ SS1[getB1(T1)] ^ SS2[getB2(T1)] ^ SS3[getB3(T1)];
    T11 = (T1 < 0) ? ((T1 & Integer.MAX_VALUE) | 0xFFFFFFFF80000000L) : T1;
    T00 += T11;
    T0 = SS0[getB0((int)T00)] ^ SS1[getB1((int)T00)] ^ SS2[getB2((int)T00)] ^ SS3[getB3((int)T00)];
    T00 = (T0 < 0) ? ((T0 & Integer.MAX_VALUE) | 0xFFFFFFFF80000000L) : T0;
    T11 += T00;
    T1 = SS0[getB0((int)T11)] ^ SS1[getB1((int)T11)] ^ SS2[getB2((int)T11)] ^ SS3[getB3((int)T11)];
    T11 = (T1 < 0) ? ((T1 & Integer.MAX_VALUE) | 0xFFFFFFFF80000000L) : T1;
    T00 += T11;
    
    L0[0] = L0[0] ^ (int)T00;
    L1[0] = L1[0] ^ (int)T11;
  }
  
  public static void seedEncrypt(byte[] pbData, int[] pdwRoundKey, byte[] outData) {
    int[] L0 = new int[1];
    int[] L1 = new int[1];
    int[] R0 = new int[1];
    int[] R1 = new int[1];
    L0[0] = 0;
    L1[0] = 0;
    R0[0] = 0;
    R1[0] = 0;
    int[] K = new int[2];
    
    L0[0] = pbData[0] & 0xFF;
    L0[0] = L0[0] << 8 ^ pbData[1] & 0xFF;
    L0[0] = L0[0] << 8 ^ pbData[2] & 0xFF;
    L0[0] = L0[0] << 8 ^ pbData[3] & 0xFF;
    
    L1[0] = pbData[4] & 0xFF;
    L1[0] = L1[0] << 8 ^ pbData[5] & 0xFF;
    L1[0] = L1[0] << 8 ^ pbData[6] & 0xFF;
    L1[0] = L1[0] << 8 ^ pbData[7] & 0xFF;
    
    R0[0] = pbData[8] & 0xFF;
    R0[0] = R0[0] << 8 ^ pbData[9] & 0xFF;
    R0[0] = R0[0] << 8 ^ pbData[10] & 0xFF;
    R0[0] = R0[0] << 8 ^ pbData[11] & 0xFF;
    
    R1[0] = pbData[12] & 0xFF;
    R1[0] = R1[0] << 8 ^ pbData[13] & 0xFF;
    R1[0] = R1[0] << 8 ^ pbData[14] & 0xFF;
    R1[0] = R1[0] << 8 ^ pbData[15] & 0xFF;
    
    if (!ENDIAN.booleanValue()) {
      endianChange(L0);
      endianChange(L1);
      endianChange(R0);
      endianChange(R1);
    } 
    
    int nCount = 0;
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(L0, L1, R0, R1, K);
    K[0] = pdwRoundKey[nCount++];
    K[1] = pdwRoundKey[nCount++];
    seedRound(R0, R1, L0, L1, K);
    
    if (!ENDIAN.booleanValue()) {
      endianChange(L0);
      endianChange(L1);
      endianChange(R0);
      endianChange(R1);
    } 
    
    for (int i = 0; i < 4; i++) {
      outData[i] = (byte)(R0[0] >>> 8 * (3 - i) & 0xFF);
      outData[4 + i] = (byte)(R1[0] >>> 8 * (3 - i) & 0xFF);
      outData[8 + i] = (byte)(L0[0] >>> 8 * (3 - i) & 0xFF);
      outData[12 + i] = (byte)(L1[0] >>> 8 * (3 - i) & 0xFF);
    } 
  }
  
  public static void seedDecrypt(byte[] pbData, int[] pdwRoundKey, byte[] outData) {
    int[] L0 = new int[1];
    int[] L1 = new int[1];
    int[] R0 = new int[1];
    int[] R1 = new int[1];
    int[] K = new int[2];
    L0[0] = 0;
    L1[0] = 0;
    R0[0] = 0;
    R1[0] = 0;
    
    L0[0] = pbData[0] & 0xFF;
    L0[0] = L0[0] << 8 ^ pbData[1] & 0xFF;
    L0[0] = L0[0] << 8 ^ pbData[2] & 0xFF;
    L0[0] = L0[0] << 8 ^ pbData[3] & 0xFF;
    
    L1[0] = pbData[4] & 0xFF;
    L1[0] = L1[0] << 8 ^ pbData[5] & 0xFF;
    L1[0] = L1[0] << 8 ^ pbData[6] & 0xFF;
    L1[0] = L1[0] << 8 ^ pbData[7] & 0xFF;
    
    R0[0] = pbData[8] & 0xFF;
    R0[0] = R0[0] << 8 ^ pbData[9] & 0xFF;
    R0[0] = R0[0] << 8 ^ pbData[10] & 0xFF;
    R0[0] = R0[0] << 8 ^ pbData[11] & 0xFF;
    
    R1[0] = pbData[12] & 0xFF;
    R1[0] = R1[0] << 8 ^ pbData[13] & 0xFF;
    R1[0] = R1[0] << 8 ^ pbData[14] & 0xFF;
    R1[0] = R1[0] << 8 ^ pbData[15] & 0xFF;
    
    if (!ENDIAN.booleanValue()) {
      endianChange(L0);
      endianChange(L1);
      endianChange(R0);
      endianChange(R1);
    } 
    
    int nCount = 47;
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(L0, L1, R0, R1, K);
    K[1] = pdwRoundKey[nCount--];
    K[0] = pdwRoundKey[nCount--];
    seedRound(R0, R1, L0, L1, K);
    
    if (!ENDIAN.booleanValue()) {
      endianChange(L0);
      endianChange(L1);
      endianChange(R0);
      endianChange(R1);
    } 
    
    for (int i = 0; i < 4; i++) {
      outData[i] = (byte)(R0[0] >>> 8 * (3 - i) & 0xFF);
      outData[4 + i] = (byte)(R1[0] >>> 8 * (3 - i) & 0xFF);
      outData[8 + i] = (byte)(L0[0] >>> 8 * (3 - i) & 0xFF);
      outData[12 + i] = (byte)(L1[0] >>> 8 * (3 - i) & 0xFF);
    } 
  }



  
  private static void encRoundKeyUpdate0(int[] K, int[] A, int[] B, int[] C, int[] D, int[] E, int[] F, int[] G, int[] H, int Z, int rot) {
    int T0 = D[0];
    
    D[0] = D[0] >>> rot ^ C[0] << 32 - rot;
    C[0] = C[0] >>> rot ^ B[0] << 32 - rot;
    B[0] = B[0] >>> rot ^ A[0] << 32 - rot;
    A[0] = A[0] >>> rot ^ T0 << 32 - rot;
    
    int T00 = (A[0] + C[0] ^ E[0]) - F[0] ^ KC[Z];
    int T11 = (B[0] - D[0] ^ G[0]) + H[0] ^ KC[Z];
    
    K[0] = SS0[getB0(T00)] ^ SS1[getB1(T00)] ^ SS2[getB2(T00)] ^ SS3[getB3(T00)];
    K[1] = SS0[getB0(T11)] ^ SS1[getB1(T11)] ^ SS2[getB2(T11)] ^ SS3[getB3(T11)];
  }



  
  private static void encRoundKeyUpdate1(int[] K, int[] A, int[] B, int[] C, int[] D, int[] E, int[] F, int[] G, int[] H, int Z, int rot) {
    int T0 = E[0];
    
    E[0] = E[0] << rot ^ F[0] >>> 32 - rot;
    F[0] = F[0] << rot ^ G[0] >>> 32 - rot;
    G[0] = G[0] << rot ^ H[0] >>> 32 - rot;
    H[0] = H[0] << rot ^ T0 >>> 32 - rot;
    
    int T00 = (A[0] + C[0] ^ E[0]) - F[0] ^ KC[Z];
    int T11 = (B[0] - D[0] ^ G[0]) + H[0] ^ KC[Z];
    
    K[0] = SS0[getB0(T00)] ^ SS1[getB1(T00)] ^ SS2[getB2(T00)] ^ SS3[getB3(T00)];
    K[1] = SS0[getB0(T11)] ^ SS1[getB1(T11)] ^ SS2[getB2(T11)] ^ SS3[getB3(T11)];
  }
  
  public static void seedRoundKey(int[] pdwRoundKey, byte[] pbUserKey) {
    int[] A = new int[1];
    int[] B = new int[1];
    int[] C = new int[1];
    int[] D = new int[1];
    int[] E = new int[1];
    int[] F = new int[1];
    int[] G = new int[1];
    int[] H = new int[1];
    
    int[] K = new int[2];

    
    int nCount = 2;
    
    A[0] = pbUserKey[0] & 0xFF;
    A[0] = A[0] << 8 ^ pbUserKey[1] & 0xFF;
    A[0] = A[0] << 8 ^ pbUserKey[2] & 0xFF;
    A[0] = A[0] << 8 ^ pbUserKey[3] & 0xFF;
    
    B[0] = pbUserKey[4] & 0xFF;
    B[0] = B[0] << 8 ^ pbUserKey[5] & 0xFF;
    B[0] = B[0] << 8 ^ pbUserKey[6] & 0xFF;
    B[0] = B[0] << 8 ^ pbUserKey[7] & 0xFF;
    
    C[0] = pbUserKey[8] & 0xFF;
    C[0] = C[0] << 8 ^ pbUserKey[9] & 0xFF;
    C[0] = C[0] << 8 ^ pbUserKey[10] & 0xFF;
    C[0] = C[0] << 8 ^ pbUserKey[11] & 0xFF;
    
    D[0] = pbUserKey[12] & 0xFF;
    D[0] = D[0] << 8 ^ pbUserKey[13] & 0xFF;
    D[0] = D[0] << 8 ^ pbUserKey[14] & 0xFF;
    D[0] = D[0] << 8 ^ pbUserKey[15] & 0xFF;
    
    E[0] = pbUserKey[16] & 0xFF;
    E[0] = E[0] << 8 ^ pbUserKey[17] & 0xFF;
    E[0] = E[0] << 8 ^ pbUserKey[18] & 0xFF;
    E[0] = E[0] << 8 ^ pbUserKey[19] & 0xFF;
    
    F[0] = pbUserKey[20] & 0xFF;
    F[0] = F[0] << 8 ^ pbUserKey[21] & 0xFF;
    F[0] = F[0] << 8 ^ pbUserKey[22] & 0xFF;
    F[0] = F[0] << 8 ^ pbUserKey[23] & 0xFF;
    
    G[0] = pbUserKey[24] & 0xFF;
    G[0] = G[0] << 8 ^ pbUserKey[25] & 0xFF;
    G[0] = G[0] << 8 ^ pbUserKey[26] & 0xFF;
    G[0] = G[0] << 8 ^ pbUserKey[27] & 0xFF;
    
    H[0] = pbUserKey[28] & 0xFF;
    H[0] = H[0] << 8 ^ pbUserKey[29] & 0xFF;
    H[0] = H[0] << 8 ^ pbUserKey[30] & 0xFF;
    H[0] = H[0] << 8 ^ pbUserKey[31] & 0xFF;
    
    if (!ENDIAN.booleanValue()) {
      A[0] = endianChange(A[0]);
      B[0] = endianChange(B[0]);
      C[0] = endianChange(C[0]);
      D[0] = endianChange(D[0]);
      E[0] = endianChange(E[0]);
      F[0] = endianChange(F[0]);
      G[0] = endianChange(G[0]);
      H[0] = endianChange(H[0]);
    } 
    
    int T0 = (A[0] + C[0] ^ E[0]) - F[0] ^ KC[0];
    int T1 = (B[0] - D[0] ^ G[0]) - H[0] ^ KC[0];
    
    pdwRoundKey[0] = SS0[getB0(T0)] ^ SS1[getB1(T0)] ^ SS2[getB2(T0)] ^ SS3[getB3(T0)];
    pdwRoundKey[1] = SS0[getB0(T1)] ^ SS1[getB1(T1)] ^ SS2[getB2(T1)] ^ SS3[getB3(T1)];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 1, 9);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 2, 9);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 3, 11);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 4, 11);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 5, 12);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 6, 12);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 7, 9);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 8, 9);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 9, 11);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 10, 11);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 11, 12);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 12, 12);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 13, 9);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 14, 9);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 15, 11);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 16, 11);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 17, 12);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 18, 12);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 19, 9);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 20, 9);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 21, 11);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate1(K, A, B, C, D, E, F, G, H, 22, 11);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
    
    encRoundKeyUpdate0(K, A, B, C, D, E, F, G, H, 23, 12);
    pdwRoundKey[nCount++] = K[0];
    pdwRoundKey[nCount++] = K[1];
  }
  
  public static int KEY_SIZE = 48;
  public static int TEXT_SIZE = 16;
  
  public static byte[] seedEncrypt(byte[] value, byte[] key) {
    int[] pdwRoundKey = new int[48];
    byte[] pbCipher = new byte[TEXT_SIZE];
    
    seedRoundKey(pdwRoundKey, key);
    seedEncrypt(value, pdwRoundKey, pbCipher);
    
    return pbCipher;
  }
  
  public static String decrypt(byte[] hash, byte[] key) {
    byte[] result = new byte[hash.length];
    int[] pdwRoundKey = new int[KEY_SIZE];
    seedRoundKey(pdwRoundKey, key);
    
    byte[] temp = new byte[TEXT_SIZE];
    byte[] temp2 = new byte[TEXT_SIZE];
    
    int loop = 0;
    if (hash.length <= TEXT_SIZE) {
      seedDecrypt(hash, pdwRoundKey, result);
    } else {
      loop = (int)Math.ceil(hash.length / TEXT_SIZE);
      for (int i = 0; i < loop; i++) {
        int j; for (j = 0; j < TEXT_SIZE; j++) {
          temp2[j] = hash[i * TEXT_SIZE + j];
        }
        seedDecrypt(temp2, pdwRoundKey, temp);
        for (j = 0; j < temp.length; j++) {
          result[i * TEXT_SIZE + j] = temp[j];
        }
      } 
    } 
    
    if (loop == 0) loop = 1; 
    result = removeAnsiX923Padding(result, TEXT_SIZE * loop);
    return new String(result, 0, result.length);
  }






  
  public static String decrypt(String value, byte[] key) {
    if (isEmpty(value)) {
      return null;
    }
    if (value.length() == 1) {
      return value;
    }
    byte[] hashArray = new byte[value.length() / 2];
    int count = 0;
    for (int i = 0; i < hashArray.length * 2; i += 2) {
      hashArray[count] = (byte)Integer.parseInt(String.valueOf(Long.decode("0x" + value.substring(i, i + 2))));
      count++;
    } 
    return decrypt(hashArray, key);
  }







  
  public static String encrypt(String value, byte[] key) throws Exception {
    if (isEmpty(value)) {
      return null;
    }
    if (value.length() == 1) {
      return value;
    }
    
    byte[] pwdByte = value.getBytes("UTF-8");
    
    int loop = (int)Math.ceil(pwdByte.length / TEXT_SIZE);
    byte[] result = new byte[TEXT_SIZE * loop];
    
    byte[] temp = new byte[TEXT_SIZE];
    if (pwdByte.length <= TEXT_SIZE) {
      temp = seedEncrypt(addAnsiX923Padding(value.getBytes("UTF-8"), TEXT_SIZE), key);
      for (int j = 0; j < temp.length; j++)
        result[j] = temp[j]; 
    } else {
      int end = 0;
      int period = TEXT_SIZE;
      for (int j = 0; j < loop; j++) {
        if (pwdByte.length - end <= TEXT_SIZE) {
          period = pwdByte.length - end;
          temp = seedEncrypt(addAnsiX923Padding((new String(pwdByte, j * TEXT_SIZE, period, "UTF-8")).getBytes("UTF-8"), TEXT_SIZE), key);
          for (int k = 0; k < temp.length; k++)
            result[j * TEXT_SIZE + k] = temp[k]; 
        } else {
          temp = seedEncrypt((new String(pwdByte, j * TEXT_SIZE, period, "UTF-8")).getBytes("UTF-8"), key);
          
          for (int k = 0; k < temp.length; k++)
            result[j * TEXT_SIZE + k] = temp[k]; 
        } 
        end += TEXT_SIZE;
      } 
    } 
    
    String resultHash = ""; byte b; int i; byte[] arrayOfByte1;
    for (i = (arrayOfByte1 = result).length, b = 0; b < i; ) { byte hash = arrayOfByte1[b];
      resultHash = String.valueOf(resultHash) + addZero(Integer.toHexString(0xFF & hash), 2); b++; }
    
    return resultHash;
  }
  
  public static String addZero(String str, int length) {
    String temp = "";
    for (int i = str.length(); i < length; i++)
      temp = String.valueOf(temp) + "0"; 
    temp = String.valueOf(temp) + str;
    return temp;
  }
  
  public static boolean isEmpty(String str) {
    if (str == null || str.length() == 0) {
      return true;
    }
    return false;
  }

  
  private static byte[] addAnsiX923Padding(byte[] source, int blockSize) {
    if (blockSize < 1)
      return null; 
    int paddingCnt = source.length % blockSize;
    byte[] paddingResult = null;
    
    if (paddingCnt != 0) {
      paddingResult = new byte[source.length + blockSize - paddingCnt];
      System.arraycopy(source, 0, paddingResult, 0, source.length);
      
      int addPaddingCnt = blockSize - paddingCnt;
      for (int i = 0; i < addPaddingCnt; i++) {
        paddingResult[source.length + i] = 0;
      }
      
      paddingResult[paddingResult.length - 1] = (byte)addPaddingCnt;
    } else {
      paddingResult = source;
    } 
    
    return paddingResult;
  }
  
  public static byte[] removeAnsiX923Padding(byte[] source, int blockSize) {
    byte[] paddingResult = null;
    boolean isPadding = false;
    
    int lastValue = source[source.length - 1];
    if (lastValue < blockSize - 1) {
      int zeroPaddingCount = lastValue - 1;
      
      for (int i = 2; i < zeroPaddingCount + 2; i++) {
        if (source[source.length - i] != 0) {
          isPadding = false;
          
          break;
        } 
      } 
      isPadding = true;
    } else {
      isPadding = false;
    } 
    
    if (isPadding) {
      paddingResult = new byte[source.length - lastValue];
      System.arraycopy(source, 0, paddingResult, 0, paddingResult.length);
    } else {
      paddingResult = source;
    } 
    
    return paddingResult;
  }
  public static String encrypt(String value, String key) throws Exception {
    return encrypt(value, key.getBytes("UTF-8"));
  }
  public static String decrypt(String value, String key) throws Exception {
    return decrypt(value, key.getBytes("UTF-8"));
  }
  public static String encrypt(String value) throws Exception {
    return encrypt(value, "aHR0cDovL3d3dy5hdC1zeXMuY28ua3I=");
  }
  public static String decrypt(String value) throws Exception {
    return decrypt(value, "aHR0cDovL3d3dy5hdC1zeXMuY28ua3I=");
  }
}

