from typing import re

from django.shortcuts import render
from rest_framework import status
from rest_framework import generics, views
from rest_framework.decorators import api_view
from rest_framework.response import Response
from steam.models import Discussion, Friends, Post, Thread, User
from steam.serializers import DiscussionSerializer, FriendsSerializer, PostSerializer, ThreadSerializer, UserSerializer, CreateUserSerializer

import ast
# Create your views here.

# CtrlAccount
# /account/
@api_view(['PUT'])
def createAccount(request):
    if request.method == 'PUT':
        serializer = CreateUserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /account/{uid}/
@api_view(['GET', 'PUT'])
def user(request, pk):
    try:
        queryset = User.objects.get(pk=pk)
    except User.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = UserSerializer(queryset)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = UserSerializer(queryset, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /account/email/{email}
@api_view(['GET'])
def checkByEmail(request, email):
    if request.method == 'GET':
        try:
            queryset = User.objects.get(email__exact=email)
        except User.DoesNotExist:
            return Response(None)
        serializer = UserSerializer(queryset)
        return Response(serializer.data)


# /account/login/
@api_view(['POST'])
def login(request):
    if request.method == 'POST':
        username = request.data.get('username', None)
        password = request.data.get('password', None)
        queryset = User.objects.get(username=username, password=password)
        serializer = UserSerializer(queryset)
        return Response(serializer.data)


# /account/search?text={text}
@api_view(['GET'])
def searchAccount(request):
    if request.method == 'GET':
        text = request.query_params.get('text', None);
        queryset = User.objects.all().filter(username__icontains=text)
        serializer = UserSerializer(queryset, many=True)
        return Response(serializer.data)


# CtrlFriend
# /friend
@api_view(['PUT'])
def addFriend(request):
    if request.method == 'PUT':
        serializer = FriendsSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /friend/confirm/{uid}/{fid}/
@api_view(['PUT'])
def confirmFriend(request, uid, fid):
    if request.method == 'PUT':
        queryset = Friends.objects.get(userid=fid, friendid=uid)
        serializer = FriendsSerializer(queryset, data=request.data, partial=True)
        if serializer.is_valid():
            dat = ast.literal_eval("{'userid': " + uid + ", 'friendid': " + fid + ", 'status': 1}")
            serializer2 = FriendsSerializer(data=dat)
            if serializer2.is_valid():
                serializer.save()
                serializer2.save()
                return Response(serializer2.data, status=status.HTTP_201_CREATED)
            return Response(serializer2.errors, status=status.HTTP_400_BAD_REQUEST)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /friend/check?uid={uid}&fid={fid}
@api_view(['GET'])
def checkFriendStatus(request):
    if request.method == 'GET':
        try:
            uid = request.query_params.get('uid', None)
            fid = request.query_params.get('fid', None)
            queryset = Friends.objects.get(userid=uid, friendid=fid)
        except Friends.DoesNotExist:
            return Response(None)
        serializer = FriendsSerializer(queryset)
        return Response(serializer.data)


# /friend/user/{uid}?all={isAll}/
@api_view(['GET'])
def getFriendByUserId(request, pk):
    if request.method == 'GET':
        stat = request.query_params.get('all', 0)
        queryset = Friends.objects.all().filter(userid=pk, status=1)
        if stat == 0:
            queryset = queryset.filter(status=1)
        serializer = FriendsSerializer(queryset, many=True)
        return Response(serializer.data)


# /friend/user/req/{uid}/
@api_view(['GET'])
def getRequestedFriendByUserId(request, pk):
    if request.method == 'GET':
        queryset = Friends.objects.all().filter(friendid=pk, status=0)
        serializer = FriendsSerializer(queryset, many=True)
        return Response(serializer.data)