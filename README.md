# live-twitch
Twitch.TV API kullanılarak favori listesindeki online yayıncıları gösteren Android uygulaması

### Kullanılan Kütüphane/Tasarım Deseni

- MVVM
- RxJava
- Dagger 2
- Retrofit
- Glide

## Önizleme

## Ayarlar

1. https://glass.twitch.tv/console/apps adresinden uygulamanızı kayıt et'e tıklayın
  - Name kısmına uygulamanızın ismini yazın
  - Redirect URL'ye https://twitchapps.com/tokengen/ adresini yazın
2. https://twitchapps.com/tokengen/ adresine girip OAUTH key'inizi alın
  - Client ID kısmına 1.kısımda oluşturmuş olduğunuz uygulamanın Client ID'sini girin
  - Scope kısmına user_read yazın
3. gradle.properties dosyasını kendinize göre düzenleyin
  - Dosya içerisinde yer alan key'leri kendi keyleriniz ile değiştirin.
