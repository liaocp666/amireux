import request from '@/utils/request'

const systemUserApi = {
  Page: '/api/v1/system/user/page',
  Delete: '/api/v1/system/user/delete',
  Add: '/api/v1/system/user/add',
  Edit: '/api/v1/system/user/edit',
  Get: '/api/v1/system/user/detail',
  Auth: '/api/v1/system/user/auth',
  BatchDelete: '/api/v1/system/user/batch-delete'
}

/**
 * 批量删除角色
 * @param data
 */
export function batchDelete (data) {
  return request({
    url: systemUserApi.BatchDelete,
    method: 'post',
    data: data
  })
}

/**
 * 删除角色
 * @param parameter
 * @returns {AxiosPromise}
 */
export function deleteuser (parameter) {
  return request({
    url: systemUserApi.Delete,
    method: 'post',
    data: parameter
  })
}

/**
 * 编辑权限
 * @param data
 * @returns {AxiosPromise}
 */
export function edituser (data) {
  return request({
    url: systemUserApi.Edit,
    method: 'post',
    data: data
  })
}

/**
 * 根据ID，查询角色详情
 * @param parameter
 * @returns {AxiosPromise}
 */
export function detail (parameter) {
  return request({
    url: systemUserApi.Get,
    method: 'get',
    params: parameter
  })
}

/**
 * 添加角色
 * @param data
 * @returns {AxiosPromise}
 */
export function adduser (data) {
  return request({
    url: systemUserApi.Add,
    method: 'post',
    data: data
  })
}

/**
 * 查询所有角色
 * @param parameter         分页条件，以及查询条件
 * @returns {AxiosPromise}
 */
export function queryPage (parameter) {
  return request({
    url: systemUserApi.Page,
    method: 'get',
    params: parameter
  })
}

/**
 * 角色授权
 * @param data   角色id，以及权限内容
 * @returns {AxiosPromise}
 */
export function auth (data) {
  return request({
    url: systemUserApi.Auth,
    method: 'post',
    data: data
  })
}
