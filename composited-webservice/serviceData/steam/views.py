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


# CtrlDiscussion
# /discussion/
@api_view(['GET', 'PUT'])
def discussion(request):
    if request.method == 'GET':
        queryset = Discussion.objects.all()
        serializer = DiscussionSerializer(queryset, many=True)
        return Response(serializer.data)
    elif request.method == 'PUT':
        serializer = DiscussionSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /discussion/{discussionid}/
@api_view(['GET'])
def getDiscussion(request, pk):
    if request.method == 'GET':
        queryset = Discussion.objects.get(pk=pk)
        serializer = DiscussionSerializer(queryset)
        return Response(serializer.data)


# /discussion/search?text={text}
@api_view(['GET'])
def searchDiscussion(request):
    if request.method == 'GET':
        text = request.query_params.get('text', None)
        queryset = Discussion.objects.all().filter(gamename__icontains=text) | Discussion.objects.all().filter(description__icontains=text)
        serializer = DiscussionSerializer(queryset, many=True)
        return Response(serializer.data)


# CtrlPost
# /post/
@api_view(['PUT'])
def createPost(request):
    if request.method == 'PUT':
        serializer = PostSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /post/{postid}/
@api_view(['GET', 'PUT'])
def post(request, pk):
    if request.method == 'GET':
        queryset = Post.objects.get(pk=pk)
        serializer = PostSerializer(queryset)
        return Response(serializer.data)
    elif request.method == 'PUT':
        queryset = Post.objects.get(pk=pk)
        serializer = PostSerializer(queryset, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /post/thread/{threadid}/
@api_view(['GET'])
def getAllPost(request, threadid):
    if request.method == 'GET':
        queryset = Post.objects.all().filter(threadid=threadid)
        serializer = PostSerializer(queryset, many=True)
        return Response(serializer.data)


# /post/user/{uid}/
@api_view(['GET'])
def getPostByUserId(request, userid):
    if request.method == 'GET':
        queryset = Post.objects.all().filter(userid=userid)
        serializer = PostSerializer(queryset, many=True)
        return Response(serializer.data)


# CtrlThread
# /thread/
@api_view(['PUT'])
def createThread(request):
    if request.method == 'PUT':
        serializer = ThreadSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /thread/{threadid}/
@api_view(['GET'])
def getThread(request, pk):
    if request.method == 'GET':
        queryset = Thread.objects.get(pk=pk)
        serializer = ThreadSerializer(queryset)
        return Response(serializer.data)


# /thread/search/{text}/{discussionid}/
@api_view(['GET'])
def searchThread(request, discussionid):
    if request.method == 'GET':
        text = request.query_params.get('text', None)
        queryset = Thread.objects.all().filter(discussionid=discussionid, title__icontains=text)
        serializer = ThreadSerializer(queryset, many=True)
        return Response(serializer.data)


# /thread/discussion/{discussionid}/{categoryid}/
@api_view(['GET'])
def getAllThread(request, disID, category):
    if request.method == 'GET':
        queryset = Thread.objects.all().filter(discussionid=disID).filter(categorytype=category).order_by('-ispinned', '-publishdatetime')
        serializer = ThreadSerializer(queryset, many=True)
        return Response(serializer.data)