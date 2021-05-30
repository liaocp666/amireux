import request from '@/utils/request'

const systemPermissionApi = {
  Tree: '/api/v1/system/permission/tree',
  Delete: '/api/v1/system/permission/delete',
  Add: '/api/v1/system/permission/add',
  Edit: '/api/v1/system/permission/edit',
  Get: '/api/v1/system/permission/detail',
  QueryByRole: '/api/v1/system/permission/queryByRole'
}

/**
 * 查询角色拥有的权限
 * @param parameter
 */
export function queryByRole (parameter) {
  return request({
    url: systemPermissionApi.QueryByRole,
    method: 'get',
    params: parameter
  })
}

export async function getTree () {
  const res = await request({
    url: systemPermissionApi.Tree,
    method: 'get'
  })
  res.data = handlerTree(res.data)
  return res.data
}

function handlerTree (data) {
  data.forEach(item => {
    item.title = item.name
    item.value = item.id
    item.key = item.id
    if (item.children) {
      handlerTree(item.children)
    }
  })
  return data
}

export function deletePermission (parameter) {
  return request({
    url: systemPermissionApi.Delete,
    method: 'post',
    data: parameter
  })
}

export function addPermission (parameter) {
  return request({
    url: systemPermissionApi.Add,
    method: 'post',
    data: parameter
  })
}

export function editPermission (parameter) {
  return request({
    url: systemPermissionApi.Edit,
    method: 'post',
    data: parameter
  })
}

export function getPermission (parameter) {
  return request({
    url: systemPermissionApi.Get,
    method: 'get',
    params: parameter
  })
}
