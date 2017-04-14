"""webservice URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from steam import views

urlpatterns = [
    url(r'^account/$', views.createAccount),
    url(r'^account/(?P<pk>[0-9]+)/$', views.user),
    url(r'^account/login', views.login),
    url(r'^account/search/$', views.searchAccount),
    url(r'^discussion/$', views.discussion),
    url(r'^discussion/(?P<pk>[0-9]+)/$', views.getDiscussion),
    url(r'^discussion/search/$', views.searchDiscussion),
    url(r'^post/$', views.createPost),
    url(r'^post/(?P<pk>[0-9]+)/$', views.post),
    url(r'^post/thread/(?P<threadid>[0-9]+)/$', views.getAllPost),
    url(r'^post/user/(?P<userid>[0-9]+)/$', views.getPostByUserId),
    url(r'^thread/$', views.createThread),
    url(r'^thread/(?P<pk>[0-9]+)/$', views.getThread),
    url(r'^thread/search/(?P<discussionid>[0-9]+)/$', views.searchThread),
    url(r'^thread/discussion/(?P<disID>[0-9]+)/(?P<category>[1-2]+)/$', views.getAllThread),
    url(r'^friend/$', views.addFriend),
    url(r'^friend/confirm/(?P<uid>[0-9])+/(?P<fid>[0-9]+)/$', views.confirmFriend),
    url(r'^friend/check/$', views.checkFriendStatus),
    url(r'^friend/user/(?P<pk>[0-9]+)/$', views.getFriendByUserId),
    url(r'^friend/user/req/(?P<pk>[0-9]+)/$', views.getRequestedFriendByUserId),
]

urlpatterns = format_suffix_patterns(urlpatterns)