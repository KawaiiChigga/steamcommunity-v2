from typing import re

from django.shortcuts import render
from rest_framework import status
from rest_framework import generics, views
from rest_framework.decorators import api_view
from rest_framework.response import Response
from steam.models import Discussion, Friends, Post, Thread, User
from steam.serializers import DiscussionSerializer, FriendsSerializer, PostSerializer, ThreadSerializer, UserSerializer, CreateUserSerializer

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


# /account/login/
@api_view(['POST'])
def login(request):
    if request.method == 'POST':
        username = request.data.get('username', None)
        password = request.data.get('password', None)
        print(username)
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
        queryset = Thread.objects.all().filter(pk=pk)
        serializer = ThreadSerializer(queryset, many=True)
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
        queryset = Thread.objects.all().filter(discussionid=disID).filter(categorytype=category)
        serializer = ThreadSerializer(queryset, many=True)
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
        queryset = Friends.objects.get(userid=uid, friendid=fid)
        serializer = FriendsSerializer(queryset, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save();
            # dat = "{'userid': " + fid + ", 'friendid': " + uid + ", 'status': 1}"
            # serializer2 = FriendsSerializer(data=dat)
            # if serializer2.is_valid():
            #     serializer.save()
            #     serializer2.save()
            #     return Response(serializer2.data, status=status.HTTP_201_CREATED)
            # return Response(serializer2.errors, status=status.HTTP_400_BAD_REQUEST)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# /friend/check?uid={uid}&fid={fid}
@api_view(['GET'])
def checkFriendStatus(request):
    if request.method == 'GET':
        uid = request.query_params.get('uid', None)
        fid = request.query_params.get('fid', None)
        queryset = Friends.objects.get(userid=uid, friendid=fid)
        serializer = FriendsSerializer(queryset)
        return Response(serializer.data)


# /friend/user/{uid}?all={isAll}/
@api_view(['GET'])
def getFriendByUserId(request, pk):
    if request.method == 'GET':
        stat = request.query_params.get('all', 0)
        queryset = Friends.objects.all().filter(userid=pk)
        if stat == 1:
            queryset = queryset.filter(status=1)
        serializer = FriendsSerializer(queryset, many=True)
        return Response(serializer.data)


# /friend/user/req/{uid}/
@api_view(['GET'])
def getRequestedFriendByUserId(request, pk):
    if request.method == 'GET':
        queryset = Friends.objects.all().filter(friendid=pk, status=0)
        serializer = FriendsSerializer(queryset)
        return Response(serializer.data)


# class UserList(generics.ListAPIView):
#     serializer_class = UserSerializer
#
#     def get_queryset(self):
#         queryset = User.objects.all()
#         userid = self.request.query_params.get('uid', None)
#         if userid is not None:
#             queryset = queryset.filter(userid=userid)
#         return queryset




# @api_view(['GET'])
# def discussion_detail(request, pk):
#     try:
#         discussion = Discussion.objects.get(pk=pk)
#     except Discussion.DoesNotExist:
#         return Response(status=status.HTTP_404_NOT_FOUND)
#
#     if request.method == 'GET':
#         serializer = DiscussionSerializer(discussion)
#         return Response(serializer.data)