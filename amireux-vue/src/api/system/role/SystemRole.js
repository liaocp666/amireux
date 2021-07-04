import request from '@/utils/request'

const systemRoleApi = {
  Page: '/api/v1/role/page',
  Delete: '/api/v1/role/delete',
  Add: '/api/v1/role/save',
  Get: '/api/v1/role/detail',
  Auth: '/api/v1/role/auth',
  BatchDelete: '/api/v1/role/delete',
  QueryByUser: '/api/v1/role/queryByUser'
}

/**
 * 查询用户拥有的角色
 * @param parameter
 */
export function queryByUser (parameter) {
  return request({
    url: systemRoleApi.QueryByUser,
    method: 'get',
    params: parameter
  })
}

/**
 * 批量删除角色
 * @param data
 */
export function batchDelete (data) {
  return request({
    url: systemRoleApi.BatchDelete,
    method: 'post',
    data: data
  })
}

/**
 * 删除角色
 * @param parameter
 * @returns {AxiosPromise}
 */
export function deleteRole (parameter) {
  return request({
    url: systemRoleApi.Delete,
    method: 'post',
    data: parameter
  })
}

/**
 * 编辑权限
 * @param data
 * @returns {AxiosPromise}
 */
export function editRole (data) {
  return request({
    url: systemRoleApi.Add,
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
    url: systemRoleApi.Get,
    method: 'get',
    params: parameter
  })
}

/**
 * 添加角色
 * @param data
 * @returns {AxiosPromise}
 */
export function addRole (data) {
  return request({
    url: systemRoleApi.Add,
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
    url: systemRoleApi.Page,
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
    url: systemRoleApi.Auth,
    method: 'post',
    data: data
  })
}
