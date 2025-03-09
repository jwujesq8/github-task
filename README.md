# GitHub API Integration Service
_Alena Żukowska - zhukovskaja.elena@gmail.com_

This project is a Java-based service for integrating with the GitHub API to fetch repository information and branches.

## Description
This service allows users to fetch a list of repositories for a given GitHub username and retrieve information about non-fork repositories. The data is fetched from the GitHub API and returned as a RESTful API service.

## Table of Contents
- [Installation](#installation)
- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Responses](#responses)

## Installation
To install this project, clone it and install the required dependencies.
     ````bash
  git clone https://github.com/jwujesq8/github-task

## Prerequisites
- Java 21
- Maven


## Configuration
- <span style="color: green;">_GITHUB_API_TOKEN_</span>: Set your GitHub API token in application.properties
  1. go to github.com
  2. open settings
  3. open Developer Settings
  4. Personal access tokens
  5. Tokens (classic)
  6. generate your github API token
  7. copy and paste it into application.properties
- <span style="color: green;">_application.properties_</span>:
    ````
    spring.application.name=Atipera-github-task
    github.api.token=your_github_api_token
  
    LIST_REPOS_BY_USERNAME_PATH=/users/{username}/repos
    LIST_BRANCHES_BY_REPOS_PATH=/repos/{owner}/{repo}/branches

## API Endpoints
- GET /github/{username}/repos: Fetch a list of repositories for a given GitHub username.
  Example usage:
    ````bash
  curl -X GET "http://localhost:8080/github/jwujesq8/repos"

## Responses
1. 200 - OK
    ````JSON
   {
        "reposName": "SQL",
        "ownerLogin": "jwujesq8",
        "branches": [
            {
                "name": "master",
                "commit": {
                    "sha": "72f7ff2c6848661fce5bb5ca7ad57a88d01bc03a"
                }
            }
        ]
    }

2. 404 - User not found
   ````JSON
   {
        "status": "404",
        "message": "User 'dfgddfdfsv' not found"
    }
