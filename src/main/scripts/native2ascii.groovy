def src = file('src/main/i18n/messages_zh_CN.properties').getPath()
def des = file('grails-app/i18n/messages_zh_CN.properties').getPath()
("native2ascii.exe ${src} ${des}").execute()