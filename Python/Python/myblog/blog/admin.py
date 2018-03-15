# -*- coding: utf-8 -*-
'''
该应用的后台管理系统配置
'''
from __future__ import unicode_literals

from django.contrib import admin

# Register your models here.
from models import Article
class ArticleAdmin(admin.ModelAdmin):
    list_display = ('title', 'content', 'pub_time')
    list_filter = ('pub_time',)
admin.site.register(Article, ArticleAdmin)