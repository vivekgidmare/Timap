/* C++ code produced by gperf version 3.0.3 */
/* Command-line: /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/gperf -L C++ -E -t /private/var/folders/cx/c2snwnn57vn9fl5zrq86nsgh0000gn/T/vivek/map-generated/KrollGeneratedBindings.gperf  */
/* Computed positions: -k'13' */

#if !((' ' == 32) && ('!' == 33) && ('"' == 34) && ('#' == 35) \
      && ('%' == 37) && ('&' == 38) && ('\'' == 39) && ('(' == 40) \
      && (')' == 41) && ('*' == 42) && ('+' == 43) && (',' == 44) \
      && ('-' == 45) && ('.' == 46) && ('/' == 47) && ('0' == 48) \
      && ('1' == 49) && ('2' == 50) && ('3' == 51) && ('4' == 52) \
      && ('5' == 53) && ('6' == 54) && ('7' == 55) && ('8' == 56) \
      && ('9' == 57) && (':' == 58) && (';' == 59) && ('<' == 60) \
      && ('=' == 61) && ('>' == 62) && ('?' == 63) && ('A' == 65) \
      && ('B' == 66) && ('C' == 67) && ('D' == 68) && ('E' == 69) \
      && ('F' == 70) && ('G' == 71) && ('H' == 72) && ('I' == 73) \
      && ('J' == 74) && ('K' == 75) && ('L' == 76) && ('M' == 77) \
      && ('N' == 78) && ('O' == 79) && ('P' == 80) && ('Q' == 81) \
      && ('R' == 82) && ('S' == 83) && ('T' == 84) && ('U' == 85) \
      && ('V' == 86) && ('W' == 87) && ('X' == 88) && ('Y' == 89) \
      && ('Z' == 90) && ('[' == 91) && ('\\' == 92) && (']' == 93) \
      && ('^' == 94) && ('_' == 95) && ('a' == 97) && ('b' == 98) \
      && ('c' == 99) && ('d' == 100) && ('e' == 101) && ('f' == 102) \
      && ('g' == 103) && ('h' == 104) && ('i' == 105) && ('j' == 106) \
      && ('k' == 107) && ('l' == 108) && ('m' == 109) && ('n' == 110) \
      && ('o' == 111) && ('p' == 112) && ('q' == 113) && ('r' == 114) \
      && ('s' == 115) && ('t' == 116) && ('u' == 117) && ('v' == 118) \
      && ('w' == 119) && ('x' == 120) && ('y' == 121) && ('z' == 122) \
      && ('{' == 123) && ('|' == 124) && ('}' == 125) && ('~' == 126))
/* The character set is not based on ISO-646.  */
#error "gperf generated tables don't work with this execution character set. Please report a bug to <bug-gnu-gperf@gnu.org>."
#endif

#line 3 "/private/var/folders/cx/c2snwnn57vn9fl5zrq86nsgh0000gn/T/vivek/map-generated/KrollGeneratedBindings.gperf"


#include <string.h>
#include <v8.h>
#include <KrollBindings.h>

#include "com.vbg.map.ViewProxy.h"
#include "com.vbg.map.AnnotationProxy.h"
#include "com.vbg.map.MapModule.h"
#include "com.vbg.map.RouteProxy.h"


#line 16 "/private/var/folders/cx/c2snwnn57vn9fl5zrq86nsgh0000gn/T/vivek/map-generated/KrollGeneratedBindings.gperf"
struct titanium::bindings::BindEntry;
/* maximum key range = 7, duplicates = 0 */

class MapBindings
{
private:
  static inline unsigned int hash (const char *str, unsigned int len);
public:
  static struct titanium::bindings::BindEntry *lookupGeneratedInit (const char *str, unsigned int len);
};

inline unsigned int
MapBindings::hash (register const char *str, register unsigned int len)
{
  static unsigned char asso_values[] =
    {
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28,  0, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28,  5, 28, 28,
      28, 28,  0, 28, 28, 28,  0, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28, 28, 28, 28, 28,
      28, 28, 28, 28, 28, 28
    };
  return len + asso_values[(unsigned char)str[12]];
}

struct titanium::bindings::BindEntry *
MapBindings::lookupGeneratedInit (register const char *str, register unsigned int len)
{
  enum
    {
      TOTAL_KEYWORDS = 4,
      MIN_WORD_LENGTH = 21,
      MAX_WORD_LENGTH = 27,
      MIN_HASH_VALUE = 21,
      MAX_HASH_VALUE = 27
    };

  static struct titanium::bindings::BindEntry wordlist[] =
    {
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""},
#line 18 "/private/var/folders/cx/c2snwnn57vn9fl5zrq86nsgh0000gn/T/vivek/map-generated/KrollGeneratedBindings.gperf"
      {"com.vbg.map.ViewProxy", ::com::vbg::map::map::ViewProxy::bindProxy, ::com::vbg::map::map::ViewProxy::dispose},
#line 21 "/private/var/folders/cx/c2snwnn57vn9fl5zrq86nsgh0000gn/T/vivek/map-generated/KrollGeneratedBindings.gperf"
      {"com.vbg.map.RouteProxy", ::com::vbg::map::map::RouteProxy::bindProxy, ::com::vbg::map::map::RouteProxy::dispose},
      {""}, {""}, {""},
#line 20 "/private/var/folders/cx/c2snwnn57vn9fl5zrq86nsgh0000gn/T/vivek/map-generated/KrollGeneratedBindings.gperf"
      {"com.vbg.map.MapModule", ::com::vbg::map::MapModule::bindProxy, ::com::vbg::map::MapModule::dispose},
#line 19 "/private/var/folders/cx/c2snwnn57vn9fl5zrq86nsgh0000gn/T/vivek/map-generated/KrollGeneratedBindings.gperf"
      {"com.vbg.map.AnnotationProxy", ::com::vbg::map::map::AnnotationProxy::bindProxy, ::com::vbg::map::map::AnnotationProxy::dispose}
    };

  if (len <= MAX_WORD_LENGTH && len >= MIN_WORD_LENGTH)
    {
      unsigned int key = hash (str, len);

      if (key <= MAX_HASH_VALUE)
        {
          register const char *s = wordlist[key].name;

          if (*str == *s && !strcmp (str + 1, s + 1))
            return &wordlist[key];
        }
    }
  return 0;
}
#line 22 "/private/var/folders/cx/c2snwnn57vn9fl5zrq86nsgh0000gn/T/vivek/map-generated/KrollGeneratedBindings.gperf"

