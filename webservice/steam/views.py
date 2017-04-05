from django.shortcuts import render
from rest_framework import status
from rest_framework import generics, views
from rest_framework.decorators import api_view
from rest_framework.response import Response
from steam.models import Discussion, Friends, Post, Thread, User
from steam.serializers import DiscussionSerializer, FriendsSerializer, PostSerializer, ThreadSerializer, UserSerializer

# Create your views here.


@api_view(['POST'])
def login(request):
    if request.method == 'POST':
        username = request.POST['user']
        Response({"message": "This is login " + username})


class DiscussionList(generics.ListAPIView):
    serializer_class = DiscussionSerializer

    def get_queryset(self):
        queryset = Discussion.objects.all()
        gamename = self.request.query_params.get('gamename', None)
        if gamename is not None:
            queryset = queryset.filter(gamename=gamename)
        return queryset


class UserList(generics.ListAPIView):
    serializer_class = UserSerializer

    def get_queryset(self):
        queryset = User.objects.all()
        userid = self.request.query_params.get('uid', None)
        if userid is not None:
            queryset = queryset.filter(userid=userid)
        return queryset




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